package transaction;

import interfaces.DataObject;

import java.util.HashMap;

public class PendingAuthorisation implements DataObject {
    private String transactionID;
    private String accountNumber;
    private String customerID;

    @Override
    public HashMap<String, String> getDetails() {
        return new HashMap<>() {{
            put("transactionID",transactionID);put("accountNumber",accountNumber);put("customerID",customerID);
        }};
    }

    @Override
    public void setDetails(HashMap<String, String> details) {
        transactionID = details.get("transactionID");
        accountNumber = details.get("accountNumber");
        customerID = details.get("customerID");
    }
}
