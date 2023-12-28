/*  Intermediate layer between UI views and database DataHandlers. Takes objects returned from the database (ArrayLists for the most part), and prepares them
    into a nice format for the UI classes to display, and vice versa. So it should work with any UI.

    This might end up not being necessary, the plan was (and still is) for the abstract DataHandler class to
    play that role, this is just a precaution in case the UI classes still end up getting
    database-specific stuff in them when I start writing them.
 */

package controller;

import account.Account;
import customer.Customer;
import database.*;
import interfaces.DataHandlerCreator;
import interfaces.DataObject;
import interfaces.DataObjectCreator;
import login.LoginObject;
import transaction.PendingAuthorisation;
import transaction.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Controller {

    /*


                to do:






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

    //   Login:
    //   new HashMap<S,S>(enteredDetails) from ui fields
    //   create new loginObject(HashMap<>(enteredDetails) -> to LoginDataHandler
    //   receive LoginObject (List<DataObject> from DataHandler)
    //       -> send LoginObject to CustomerDataHandler
    //       receive Customer from DataHandler -> to ui

    public Customer loginAttempt (String username, String password) {
        LoginObject login = (LoginObject) objectCreator.createLoginObject(username, password);
        List<DataObject> returnedData = dataHandlerCreator.createLoginDataHandler(login).getRecords();
        if (returnedData.isEmpty()) return null;
        else {
            login = (LoginObject) returnedData.getFirst();
            returnedData.clear();
            returnedData = dataHandlerCreator.createCustomerDataHandler(login).getRecords();
            return (Customer) returnedData.getFirst();
        }
    }

    // -> send Customer to AccountDataHandler
    // receive List<DataObject> Accounts -> to ui

    public List<DataObject> getCustomerAccounts (Customer inputCustomer){
        return dataHandlerCreator.createAccountDataHandler(inputCustomer).getRecords();
    }

//    send Account to TransactionDataHandler
//    receive List<DataObject> Transactions -> to ui
//    send [Customer/Account] to AuthorisationDataHandler to get List<PendingAuthorisation> -> to ui

    public List<DataObject> getAccountTransactions (Account inputAccount) {
        return dataHandlerCreator.createTransactiontDataHandler(inputAccount).getRecords();
    }

    public List<DataObject> getPendingTransactionsForAccount(Account inputAccount) {
        return dataHandlerCreator.createAuthorisationDataHandler(inputAccount).getRecords();
    }

    public List<DataObject> getPendingTransactionsForCustomer(Customer inputCustomer) {
        return dataHandlerCreator.createAuthorisationDataHandler(inputCustomer).getRecords();
    }

//    Transaction (deposit, withdraw, transfer) request from ui
//    signatories = 1
//    create Transaction  -> send to TransactionDataHandler
//                                            -> send to AccountDataHandler to update balance
//    signatories > 1
//    create Transaction (authorisation column = pending)
//                        -> send to TransactionDataHandler (writeNew) (don't send to AccountHandler, unless we're going to show put funds on hold?)
//            -> send Account to signatories to get CustomerIDs
//    create PendingAuthorisation for each customerID
//                            -> send List<PendingAuthorisation> to AuthorisationDataHandler (writeAll)

    public void newTransaction(Transaction inputTransaction) {
        dataHandlerCreator.createTransactiontDataHandler(inputTransaction).writeNewRecord();
        dataHandlerCreator.createAccountDataHandler(inputTransaction).update();
    }
    // DOESN'T HANDLE TRANSFERS YET! - needs to split into two transactions to show correctly
    // on each account (same transaction ID?)

    public void newPendingTransaction(Transaction inputTransaction) {
        dataHandlerCreator.createTransactiontDataHandler(inputTransaction).writeNewRecord();
        List<DataObject> results = dataHandlerCreator.createSignatoryDataHandler(inputTransaction).getRecords();
        String transactionID = inputTransaction.getDetails().get("transactionID");
        ArrayList<DataObject> pendingTransactions = new ArrayList<>();
        for (DataObject result : results) {
            String customerID = result.getDetails().get("customerID");
            String accountNumber = result.getDetails().get("accountNumber");
            HashMap<String, String> resultMap = new HashMap<>();
            resultMap.put("transactionID",transactionID);
            resultMap.put("customerID", customerID);
            resultMap.put("accountNumber", accountNumber);
            pendingTransactions.add(objectCreator.createPendingAuthorisation(resultMap));
        }
        AuthorisationDataHandler x = (AuthorisationDataHandler) dataHandlerCreator.createAuthorisationDataHandler(pendingTransactions);
        x.writeAllRecords();
    }

//    confirmation of authorisation from ui
//    send PendingAuthorisation -> AuthorisationDataHandler (delete)
//                    if last PendingAuthorisation for that transaction
//                        -> send PendingAuthorisation -> TransactionDataHandler (update)
//                        -> send Transaction -> AccountDataHandler (update)

        public void confirmPendingAuthorisation(PendingAuthorisation inputObject) {
            dataHandlerCreator.createAuthorisationDataHandler(inputObject).delete();
            List<DataObject> remainingPending = dataHandlerCreator.createAuthorisationDataHandler(inputObject).getRecords();
            if (remainingPending.isEmpty()) {
                dataHandlerCreator.createTransactiontDataHandler(inputObject).update();
            }
            /*  Needs a database table/object redesign to complete this (pending_authorisation
                table needs to hold the full transaction details, then on completion write a
                brand new transaction to the main transaction table (rather than update an old
                row and have to recalculate previous/new balance etc)).
            */

        }

    //    create new customer request from ui
    //    Create LoginObject (username,password) -> to LoginDataHandler (writeNew)
    //    receive LoginObject from LoginDataHandler with assigned CustomerID
    //    create Customer -> to CustomerDataHandler (writeNew)

        public void createNewCustomer(LoginObject inputlogin, Customer inputObject) {
           DataHandler newLogin = dataHandlerCreator.createLoginDataHandler(inputlogin);
           newLogin.writeNewRecord();
           String customerID = newLogin.getRecords().getFirst().getDetails().get("customerID");
           inputObject.setCustomerID(customerID);
           dataHandlerCreator.createCustomerDataHandler(inputObject).writeNewRecord();
        }



}
