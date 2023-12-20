package login;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "login")
public class LoginObject {

    @DatabaseField
    private String username;

    @DatabaseField
    private String password;

    @DatabaseField(generatedId = true, columnName = "customer_id")
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
