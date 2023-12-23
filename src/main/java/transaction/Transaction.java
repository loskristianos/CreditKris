package transaction;

import java.util.HashMap;

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

    public Transaction(HashMap<String, String> transactionDetails) {
        setDetails(transactionDetails);
    }
    public String[] getTransactionDetails() {
        return new String[] {transactionID, accountNumber, transactionAmount, transactionType, previousBalance, newBalance, transactionTime, authorised, additionalInfo};
    }

    public HashMap<String, String> getDetails() {
        HashMap<String, String> details = new HashMap<>();
        details.put("transactionID",transactionID);
        details.put("accountNumber",accountNumber);
        details.put("transactionAmount", transactionAmount);
        details.put("transactionType",transactionType);
        details.put("previousBalance",previousBalance);
        details.put("newBalance",newBalance);
        details.put("transactionTime",transactionTime);
        details.put("authorised",authorised);
        details.put("additionalInfo",additionalInfo);
        return details;
    }

    public void setDetails(HashMap<String, String> details) {
        transactionID = details.get("transactionID");
        accountNumber = details.get("accountNumber");
        transactionAmount = details.get("transactionAmount");
        transactionType = details.get("transactionType");
        previousBalance = details.get("previousBalance");
        newBalance = details.get("newBalance");
        transactionTime = details.get("transactionTime");
        authorised = details.get("authorised");
        additionalInfo = details.get("additionalInfo");
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
