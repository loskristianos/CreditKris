package transaction;

import interfaces.DataObject;

import java.util.HashMap;

public class PendingAuthorisation implements DataObject {
    private String objectType = "Transaction";
    private String transactionID;
    private String accountNumber;
    private String customerID;
    private String transactionAmount;
    private String transactionType;

    public PendingAuthorisation(){}

    public PendingAuthorisation(HashMap<String,String> details){
        setDetails(details);
    }

    @Override
    public HashMap<String, String> getDetails() {
        return new HashMap<>() {{
            put("transactionID",transactionID);
            put("accountNumber",accountNumber);
            put("customerID",customerID);
            put("transactionAmount", transactionAmount);
            put("transactionType", transactionType);
        }};
    }

    @Override
    public void setDetails(HashMap<String, String> details) {
        transactionID = details.get("transactionID");
        accountNumber = details.get("accountNumber");
        customerID = details.get("customerID");
        transactionAmount= details.get("transactionAmount");
        transactionType = details.get("transactionType");
    }

    @Override
    public String getObjectType() {
        return objectType;
    }
}
