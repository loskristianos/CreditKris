package transaction;

abstract class Transaction {
    private String transactionID;
    private String accountNumber;
    private String transactionAmount;
    private String transactionType;
    private String previousBalance;
    private String newBalance;
    private String transactionTime;
    private String authorised;
    private String additionalInfo;

    public Transaction(){}

    public Transaction(String[] transactionDetails) {
        setTransactionDetails(transactionDetails);
    }

    public String[] getTransactionDetails() {
        return new String[] {transactionID, accountNumber, transactionAmount, transactionType, previousBalance, newBalance, transactionTime, authorised, additionalInfo};
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
        additionalInfo = details[8];
    }

    void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    void setAdditionalInfo(String info) {
        this.additionalInfo = info;
    }
}
