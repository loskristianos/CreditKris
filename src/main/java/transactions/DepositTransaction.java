package transactions;

public class DepositTransaction extends Transaction {

    public DepositTransaction(){
        super();
    }

    public DepositTransaction(String[] transactionDetails) {
        super(transactionDetails);
        setTransactionType("Deposit");
    }



    public void amendBalance(){
        // method to add transactionAmount to currentBalance
    }
}
