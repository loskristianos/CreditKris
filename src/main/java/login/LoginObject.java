package login;

import customer.Customer;
import dao.CustomerDAO;
import dao.LoginDAO;
import interfaces.DataObject;

import java.util.HashMap;

public class LoginObject implements DataObject {

    private String username;

    private String password;

    private String customerID;

    public LoginObject() {}     // no-args constructor not used

    public LoginObject(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginObject(HashMap<String, String> details) {
        setDetails(details);
    }

    @Override
    public HashMap<String, String> getDetails() {
       return new HashMap<>() {{
           put("username",username);put("password",password);put("customerID",customerID);
        }};
    }

    public void setDetails(HashMap<String,String> details) {
        this.username = details.get("username");
        this.password = details.get("password");
        this.customerID = details.get("customerID");
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

    public Customer loginAttempt(){
        LoginObject confirmedLogin = new LoginDAO(this).getLogin();
        if (confirmedLogin != null) {
            return new CustomerDAO(confirmedLogin).getRecord();
        } else {
            return null;
        }
    }

    public LoginObject write(){
        new LoginDAO(this).write();
        return new LoginDAO(this).getLogin();
    }
}
