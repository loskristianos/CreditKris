package newobjects;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "signatories")
public class Signatory {

    @DatabaseField
    private Integer customerID;
    @DatabaseField
    private Integer accountNumber;

    private Signatory(Integer customerID, Integer accountNumber){
        setCustomerID(customerID);
        setAccountNumber(accountNumber);
    }

    public static Signatory createSignatory(Integer customerID, Integer accountNumber){
        return new Signatory(customerID, accountNumber);
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }
}
