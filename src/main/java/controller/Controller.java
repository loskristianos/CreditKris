/*  Intermediate layer between UI views and database DataHandlers. Takes objects returned from the database
    (ArrayLists of DataObjects for the most part), and prepares them into a nice format
    for the UI classes to display, and vice versa. So it should work with any UI.

    Might need a controller on the UI side as well to put the input fields into HashMaps
    and create the DataObject to send here because I don't really want that in all the UI
    classes. Or I might refactor this later to take HashMaps as input instead.
 */

package controller;

import account.*;
import customer.Customer;
import database.*;
import interfaces.*;
import login.LoginObject;
import transaction.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Controller {

    DataObjectCreator objectCreator;
    DataHandlerCreator dataHandlerCreator;

    public Controller (DataObjectCreator objectCreator, DataHandlerCreator dataHandlerCreator) {
        this.objectCreator = objectCreator;
        this.dataHandlerCreator = dataHandlerCreator;
    }


    // customer login, successful login returns Customer object (failed login returns null)
    // method added complete
//    public DataObject loginAttempt (LoginObject login) {
//        List<DataObject> returnedData = dataHandlerCreator.createLoginDataHandler(login).getRecords();
//        if (returnedData.isEmpty()) return null;
//        else {
//            String customerID = returnedData.getFirst().getDetails().get("customerID");
//            login.setCustomerID(customerID);
//            List<DataObject> customerData = dataHandlerCreator.createCustomerDataHandler(login).getRecords();
//            return customerData.getFirst();
//        }
//    }

    // get all accounts for a customer
    // method added to Customer (complete)
//    public List<DataObject> getCustomerAccounts (Customer inputCustomer){
//        return dataHandlerCreator.createAccountDataHandler(inputCustomer).getRecords();
//    }

    // get all completed transactions for an account
    // method added to Account (COMPLETE)
//    public List<DataObject> getAccountTransactions (Account inputAccount) {
//        return dataHandlerCreator.createTransactionDataHandler(inputAccount).getRecords();
//    }

    // get all pending transactions for an account
    // method added to Account (no body)
    public List<DataObject> getPendingTransactionsForAccount(Account inputAccount) {
        return dataHandlerCreator.createAuthorisationDataHandler(inputAccount).getRecords();
    }

    // get all transactions awaiting authorisation for a customer
//    public List<DataObject> getPendingTransactionsForCustomer(Customer inputCustomer) {
//        return dataHandlerCreator.createAuthorisationDataHandler(inputCustomer).getRecords();
//    }

    // create new authorised transaction
    // method added to Transaction - complete
//    public void newTransaction(Transaction inputTransaction) {
//        String transactionType = inputTransaction.getTransactionType();
//        if (transactionType.equals("Deposit") || transactionType.equals("Withdrawal")) {
//            dataHandlerCreator.createTransactionDataHandler(inputTransaction).writeNewRecord();
//            dataHandlerCreator.createAccountDataHandler(inputTransaction).update();
//        }
//        else if (transactionType.equals("Transfer")) {
//            HashMap<String,String> transferDetails = inputTransaction.getDetails();
//            String transferFrom = transferDetails.get("accountNumber");
//            String transferTo = transferDetails.get("additionalInfo");
//            Transaction transferOut = (Transaction) objectCreator.createNewTransaction(transferDetails);
//            transferOut.setTransactionType("TransferOut");
//            transferDetails.put("accountNumber", transferTo);
//            transferDetails.put("additionalInfo", transferFrom);
//            Transaction transferIn = (Transaction) objectCreator.createNewTransaction(transferDetails);
//            Account payeeAccount = (Account) dataHandlerCreator.createAccountDataHandler(transferIn).getRecords().getFirst();
//            String currentBalance = payeeAccount.getCurrentBalance();
//            transferIn.setPreviousBalance(currentBalance);
//            transferIn.setTransactionType("transferIn");
//            transferIn.setNewBalance(transferIn.calculateNewBalance());
//            dataHandlerCreator.createTransactionDataHandler(transferOut).writeNewRecord();
//            dataHandlerCreator.createAccountDataHandler(transferOut).update();
//            dataHandlerCreator.createTransactionDataHandler(transferIn).writeNewRecord();
//            dataHandlerCreator.createAccountDataHandler(transferIn).update();
//        }
//    }

    // create new transaction requiring authorisation by signatories
//    public void newPendingTransaction(Transaction inputTransaction) {
//        HashMap<String,String> transactionDetails = inputTransaction.getDetails();
//        String accountNumber = transactionDetails.get("accountNumber");
//        String transactionAmount = transactionDetails.get("transactionAmount");
//        String transactionType = transactionDetails.get("transactionType");
//        String additionalInfo = transactionDetails.get("additionalInfo");
//        String transactionID = accountNumber + System.currentTimeMillis();
//        List<DataObject> results = dataHandlerCreator.createSignatoryDataHandler(inputTransaction).getRecords();
//        ArrayList<DataObject> pendingTransactions = new ArrayList<>();
//        for (DataObject result : results) {
//            String customerID = result.getDetails().get("customerID");
//            HashMap<String, String> resultMap = new HashMap<>();
//            resultMap.put("transactionID", transactionID);
//            resultMap.put("customerID", customerID);
//            resultMap.put("accountNumber", accountNumber);
//            resultMap.put("transactionAmount", transactionAmount);
//            resultMap.put("transactionType", transactionType);
//            resultMap.put("additionalInfo", additionalInfo);
//            pendingTransactions.add(objectCreator.createPendingAuthorisation(resultMap));
//        }
//        AuthorisationDataHandler x = (AuthorisationDataHandler) dataHandlerCreator.createAuthorisationDataHandler(pendingTransactions);
//        x.writeAllRecords();
//    }

        //  authorise a pending transaction (includes procedure when authorisation being confirmed
        //  is the last pending authorisation for that transaction)
        public void confirmPendingAuthorisation(PendingTransaction inputObject) {
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
        // method added to Customer (no body yet)
        public Customer createNewCustomer(LoginObject inputLogin, Customer inputObject) {
           DataHandler newLogin = dataHandlerCreator.createLoginDataHandler(inputLogin);
           newLogin.writeNewRecord();
           String customerID = newLogin.getRecords().getFirst().getDetails().get("customerID");
           inputObject.setCustomerID(customerID);
           DataHandler newCustomer = dataHandlerCreator.createCustomerDataHandler(inputObject);
           newCustomer.writeNewRecord(); return inputObject;
        }

        //  create new account for a customer with no additional signatories
        // method added to Account (complete)
//        public void createAccount(Account inputObject) {
//            DataHandler newAccount = dataHandlerCreator.createAccountDataHandler(inputObject);
//            newAccount.writeNewRecord();
//        }

        //  create new account for a customer with additional signatories
        //  to add to write method in Account
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
