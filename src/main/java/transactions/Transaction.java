package transactions;

import com.j256.ormlite.field.DatabaseField;

abstract class Transaction {
    @DatabaseField (columnName = "transaction_id", generatedId = true)  // generated when transaction is saved
    private Integer transactionID;
    @DatabaseField (columnName = "account_number", foreign = true)
    private String accountNumber;
    @DatabaseField (columnName = "transaction_amount")
    private String transactionAmount;
    @DatabaseField (columnName = "transaction_type")
    private String transactionType;
    @DatabaseField (columnName = "previous_balance")
    private String previousBalance;
    @DatabaseField (columnName = "new_balance")
    private String newBalance;
    @DatabaseField (columnName = "transaction_time")
    private String transactionTime;
    @DatabaseField
    private String authorised;
    @DatabaseField (columnName = "additional_info")
    private String additionalInfo;

    public Transaction(){}

    public Transaction(String[] transactionDetails) {
        setTransactionDetails(transactionDetails);
    }

    public String[] getTransactionDetails() {
        return new String[] {String.valueOf(transactionID), accountNumber, transactionAmount, transactionType, previousBalance, newBalance, transactionTime, authorised, additionalInfo};
    }

    public void setTransactionDetails(String[] details){
        transactionID = Integer.valueOf(details[0]);
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
