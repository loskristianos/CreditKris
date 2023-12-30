/*  Intermediate layer between UI views and database DataHandlers. Takes objects returned from the database
    (ArrayLists of DataObjects for the most part), and prepares them into a nice format
    for the UI classes to display, and vice versa. So it should work with any UI.

    Might need a controller on the UI side as well to put the input fields into HashMaps
    and create the DataObject to send here because I don't really want that in all the UI
    classes. Or I might refactor this later to take HashMaps as input instead.
 */

package controller;

import account.Account;
import account.Signatory;
import customer.Customer;
import database.*;
import interfaces.*;
import login.LoginObject;
import transaction.*;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Controller {

    /*        to do:

                open new account request from ui
                    Create Account object, populate from ui
                        send account to AccountDataHandler (writeNew)
                    signatories > 1
                      for each:
                        create Customer -> CustomerDataHandler to check for existing Customer
                            -> follow create new customer process (new login etc.)
                            -> AccountDataHandler to check for existing accounts
                            create signatory object - SignatoryDataHandler (writeNew)
     */

    DataObjectCreator objectCreator;
    DataHandlerCreator dataHandlerCreator;
    public Controller (DataObjectCreator objectCreator, DataHandlerCreator dataHandlerCreator){
        this.objectCreator = objectCreator;
        this.dataHandlerCreator = dataHandlerCreator;
    }


    // customer login procedure
    public Customer loginAttempt (String username, String password) {
        LoginObject login = (LoginObject) objectCreator.createLoginObject(username, password);
        List<DataObject> returnedData = dataHandlerCreator.createLoginDataHandler(login).getRecords();
        if (returnedData.isEmpty()) return null;
        else {
            String customerID = returnedData.getFirst().getDetails().get("customerID");
            List<DataObject> customerData = dataHandlerCreator.createCustomerDataHandler(login).getRecords();
            return (Customer) customerData.getFirst();
        }
    }

    // get all accounts for a customer
    public List<DataObject> getCustomerAccounts (Customer inputCustomer){
        return dataHandlerCreator.createAccountDataHandler(inputCustomer).getRecords();
    }

    // get all completed transactions for an account
    public List<DataObject> getAccountTransactions (Account inputAccount) {
        return dataHandlerCreator.createTransactiontDataHandler(inputAccount).getRecords();
    }

    // get all pending transactions for an account
    public List<DataObject> getPendingTransactionsForAccount(Account inputAccount) {
        return dataHandlerCreator.createAuthorisationDataHandler(inputAccount).getRecords();
    }

    // get all transactions awaiting authorisation for a customer
    public List<DataObject> getPendingTransactionsForCustomer(Customer inputCustomer) {
        return dataHandlerCreator.createAuthorisationDataHandler(inputCustomer).getRecords();
    }

    // create new authorised transaction
    public void newTransaction(Transaction inputTransaction) {
        dataHandlerCreator.createTransactiontDataHandler(inputTransaction).writeNewRecord();
        dataHandlerCreator.createAccountDataHandler(inputTransaction).update();
    }
    // DOESN'T HANDLE TRANSFERS YET! - needs to split into two transactions to show correctly
    // on each account (same transaction ID?)

    // create new transaction requiring authorisation by signatories
    public void newPendingTransaction(Transaction inputTransaction) {
        String accountNumber = inputTransaction.getDetails().get("accountNumber");
        String transactionAmount = inputTransaction.getDetails().get("transactionAmount");
        String transactionType = inputTransaction.getDetails().get("transactionType");
        String transactionID = accountNumber + System.currentTimeMillis();
        List<DataObject> results = dataHandlerCreator.createSignatoryDataHandler(inputTransaction).getRecords();
        ArrayList<DataObject> pendingTransactions = new ArrayList<>();
        for (DataObject result : results) {
            String customerID = result.getDetails().get("customerID");
            HashMap<String, String> resultMap = new HashMap<>();
            resultMap.put("transactionID", transactionID);
            resultMap.put("customerID", customerID);
            resultMap.put("accountNumber", accountNumber);
            resultMap.put("transactionAmount", transactionAmount);
            resultMap.put("transactionType", transactionType);
            pendingTransactions.add(objectCreator.createPendingAuthorisation(resultMap));
        }
        AuthorisationDataHandler x = (AuthorisationDataHandler) dataHandlerCreator.createAuthorisationDataHandler(pendingTransactions);
        x.writeAllRecords();
    }
        //  authorise a pending transaction (includes procedure when authorisation being confirmed
        //  is the last pending authorisation for that transaction)
        public void confirmPendingAuthorisation(PendingAuthorisation inputObject) {
            dataHandlerCreator.createAuthorisationDataHandler(inputObject).delete();
            List<DataObject> remainingPending = dataHandlerCreator.createAuthorisationDataHandler(inputObject).getRecords();
            if (remainingPending.isEmpty()) {
                HashMap<String, String> transactionDetails = inputObject.getDetails();
                transactionDetails.remove("transactionID");     // remove the generated pendingAuthorisation transactionID from HashMap
                Transaction completeTransaction = (Transaction) objectCreator.createNewTransaction(transactionDetails);
                Account account = (Account) dataHandlerCreator.createAccountDataHandler(inputObject).getRecords().getFirst();
                String currentBalance = account.getCurrentBalance();
                completeTransaction.setPreviousBalance(currentBalance);
                completeTransaction.setNewBalance(completeTransaction.calculateNewBalance());
                newTransaction(completeTransaction);
                }
        }

        // create new customer record (including login details)
        public void createNewCustomer(LoginObject inputlogin, Customer inputObject) {
           DataHandler newLogin = dataHandlerCreator.createLoginDataHandler(inputlogin);
           newLogin.writeNewRecord();
           String customerID = newLogin.getRecords().getFirst().getDetails().get("customerID");
           inputObject.setCustomerID(customerID);
           dataHandlerCreator.createCustomerDataHandler(inputObject).writeNewRecord();
        }

        //  create new account for a customer with no additional signatories
        public void createAccount(Account inputObject) {
            DataHandler newAccount = dataHandlerCreator.createAccountDataHandler(inputObject);
            newAccount.writeNewRecord();
        }

        //  create new account for a customer with additional signatories
        public void createAccountWithSignatories(Account inputAccount, List<DataObject> signatoryList) {
            DataHandler newAccount = dataHandlerCreator.createAccountDataHandler(inputAccount);
            newAccount.writeNewRecord();
            Account createdAccount = (Account) newAccount.getRecords().getFirst();
            String newAccountNumber = createdAccount.getDetails().get("accountNumber");
            List<DataObject> outputList = new ArrayList<>();
            for (DataObject dataObject : signatoryList) {
                String customerID = dataObject.getDetails().get("customerID");
                HashMap<String,String> signatoryDetails = new HashMap<>();
                signatoryDetails.put("customerID",customerID);
                signatoryDetails.put("accountNumber",newAccountNumber);
                Signatory listEntry = new Signatory(signatoryDetails);
                outputList.add(listEntry);
            }
            SignatoryDataHandler newSignatories = new SignatoryDataHandler(outputList);
            newSignatories.writeAllRecords();
        }
}
