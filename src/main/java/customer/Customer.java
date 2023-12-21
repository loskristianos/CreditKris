package customer;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable (tableName = "customer")
public class Customer {
    @DatabaseField (columnName = "customer_id", id = true, foreign = true)
    private String customerID;
    @DatabaseField (columnName = "first_name")
    private String firstName;
    @DatabaseField (columnName = "last_name")
    private String lastName;
    @DatabaseField
    private String dob;
    @DatabaseField
    private String address1;
    @DatabaseField
    private String address2;
    @DatabaseField (columnName = "town")
    private String addressTown;
    @DatabaseField (columnName = "postcode")
    private String addressPostcode;

    public Customer(){}

    public Customer(String[] customerDetails) {
        setCustomerDetails(customerDetails);
    }

    public String[] getCustomerDetails() {
        return new String[] {customerID, firstName, lastName,dob, address1, address2, addressTown, addressPostcode};
    }

    public void setCustomerDetails(String[] details) {
        customerID = details[0];
        firstName = details[1];
        lastName = details[2];
        dob = details[3];
        address1 = details[4];
        address2 = details[5];
        addressTown = details[6];
        addressPostcode = details[7];
    }
}
