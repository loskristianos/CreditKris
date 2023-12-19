package transactions;

abstract class Transaction {
    private String transactionID;
    private String accountNumber;
    private String transactionAmount;
    private String transactionType;
    private String previousBalance;
    private String newBalance;
    private String transactionTime;
    private String authorised;

    public Transaction(){}

    public Transaction(String[] transactionDetails) {
        setTransactionDetails(transactionDetails);
    }

    public String[] getTransactionDetails() {
        return new String[] {transactionID, accountNumber, transactionAmount, transactionType, previousBalance, newBalance, transactionTime, authorised};
    }

    public void setTransactionDetails(String[] details){
        transactionID = details[0];
        accountNumber = details[1];
        transactionAmount = details[2];
        transactionType = details[3];
        previousBalance = details[4];
        newBalance = details[5];
        transactionTime = details[6];
        authorised = details[7];
    }

    void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
    abstract void amendBalance();   // void for now - I think this should set the balance and write it to the database
                                    // where the account object will pick up the new value rather than just return the
                                    // value from here as well. Will consider further.

    // send transaction to database from here? Separate method (will need amendBalance to return a value if so)

}
