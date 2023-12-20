package customer;

public class Customer {
    private String customerID;
    private String firstName;
    private String lastName;
    private String dob;
    private String address1;
    private String address2;
    private String addressTown;
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
