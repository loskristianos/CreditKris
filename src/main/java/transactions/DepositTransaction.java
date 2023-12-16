package transactions;

public class DepositTransaction extends Transaction {

    public DepositTransaction(String[] accountDetails, double transactionAmount, double currentBalance) {
        super(accountDetails, transactionAmount, currentBalance);
    }

    public void amendBalance(){
        // method to add transactionAmount to currentBalance
    }
}
