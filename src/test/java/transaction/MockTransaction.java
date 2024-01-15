/*
    For unit-testing purposes, this is a concrete implementation of the abstract Transaction class, with all
    methods identical except that calls to DAO methods are stubbed, so we can test logic/return values etc.
    without having to rely on a database connection. And also because my test cases using Mockito to do this
    were getting ridiculous
 */
package transaction;

import account.Account;


import java.util.HashMap;


public class MockTransaction extends Transaction{

    public MockTransaction(Account account, String transactionAmount){
        super(account,transactionAmount);
    }

    public MockTransaction(HashMap<String,String> inputMap){
        super(inputMap);
    }

    public int writeData(){
        if (overdraftCheck()<0) return -3;
        setTransactionID(getTransactionID());
        if (account.getSignatories().equals("0") || getAuthorised()==1 ||getTransactionType().equals("Transfer In") || getTransactionType().equals("Deposit")) {
           // new TransactionDAO(this).write();
           // new AccountDAO(account).update();
            return 0;
        } else {
          //  createPendingTransactions();
            return -2;
        }
    }

//    public void createPendingTransactions(){
//        List<Signatory> signatoryList = account.getSignatoryList();
//        String customerName = getInitiatingCustomer().getFullName();
//        for (Signatory signatory : signatoryList){
//            if (!getCustomerID().equals(signatory.getCustomerID())) {    // prevents creation of pending transaction for customer initiating this transaction
//                PendingTransaction pendingTransaction = new PendingTransaction(account, this);
//                pendingTransaction.setSignatoryID(signatory.getCustomerID());
//                pendingTransaction.setCustomerName(customerName);
//                if (getTargetAccountNumber() != null) {pendingTransaction.setTargetAccountNumber(getTargetAccountNumber());}
//                pendingTransaction.writeData();
//            }
//        }
//    }

    @Override
    public String calculateNewBalance() {
        return null;
    }
}
