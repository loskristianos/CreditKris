package transactions;
/*  This is going to be a bit trickier - it will need a way to get passed the other account
    that the money is being transferred to and amend both balances on the database.
    Also: would this be one transaction row or two in the database table (i.e. one row with 'from'
    and 'to' fields, or one transaction row for the 'send' and another for the 'receive'?)
 */
public class TransferTransaction extends Transaction {

    public TransferTransaction(String[] accountDetails, double transactionAmount, double currentBalance) {
        super(accountDetails, transactionAmount, currentBalance);
    }
    public void amendBalance() {

    }
}
