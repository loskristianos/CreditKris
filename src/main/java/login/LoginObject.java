package login;

public class LoginObject {

    private String username;

    private String password;

    private String customerID;

    private boolean authorised;

    public LoginObject() {}

    public LoginObject(String username, String password) {
        this.username = username;
        this.password = password;
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
