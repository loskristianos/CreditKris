package login;

import interfaces.DataObject;

import java.util.HashMap;

public class LoginObject implements DataObject {

    private String username;

    private String password;

    private String customerID;

    private boolean authorised;

    public LoginObject() {}     // no-args constructor not used

    public LoginObject(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public HashMap<String, String> getDetails() {
        HashMap<String, String> details = new HashMap<>() {{
           put("username",username);put("password",password);put("customerID",customerID);
        }};
        return details;
    }

    public void setDetails(HashMap<String,String> details) {
        this.username = details.get("username");
        this.password = details.get("password");
        this.customerID = details.get("customerID");
    }

    public void setAuthorised(boolean authorised) {
        this.authorised = authorised;
    }

    public void setCustomerID(String customerID) {
         this.customerID = customerID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCustomerID() {
        return customerID;
    }

    public boolean getAuthorised() {
        return authorised;
    }
}
