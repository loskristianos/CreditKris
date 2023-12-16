package transactions;

public class WithdrawalTransaction extends Transaction {

    public WithdrawalTransaction(String[] accountDetails, double transactionAmount, double currentBalance) {
        super(accountDetails, transactionAmount, currentBalance);
    }

    public void amendBalance(){
        // method to deduct transactionAmount from currentBalance
    }
}
