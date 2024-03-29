package account;

import dao.SignatoryDAO;
import interfaces.DataObject;

import java.util.HashMap;

public class Signatory implements DataObject {

    public Signatory(HashMap<String, String> inputDetails){setDetails(inputDetails);}
    private String accountNumber;
    private String customerID;

    @Override
    public HashMap<String, String> getDetails() {
        return new HashMap<>() {{
            put("accountNumber", accountNumber); put("customerID", customerID);
        }};
    }

    @Override
    public void setDetails(HashMap<String, String> details) {
            accountNumber = details.get("accountNumber");
            customerID = details.get("customerID");
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void writeData(){
        new SignatoryDAO(this).write();
    }
}
