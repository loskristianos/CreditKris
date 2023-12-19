package transactions;

public class WithdrawalTransaction extends Transaction {

    public WithdrawalTransaction(){
        super();
    }

    public WithdrawalTransaction(String[] transactionDetails) {
        super(transactionDetails);
        setTransactionType("Withdrawal");
    }

    public void amendBalance(){
        // method to deduct transactionAmount from currentBalance
    }
}
