package login;


public class LoginObject {

    private String username;

    private String password;

    private int customerID;     // this is generated when the record is first written to the database, so we don't need a method to set it

    private boolean authorised;

    public LoginObject() {}

    public LoginObject(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setAuthorised(boolean authorised) {
        this.authorised = authorised;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getCustomerID() {
        return customerID;
    }

    public boolean getAuthorised() {
        return authorised;
    }
}
