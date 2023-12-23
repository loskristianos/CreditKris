package customer;

import interfaces.Banking;

import java.util.HashMap;

public class Customer implements Banking {
    private String customerID;
    private String firstName;
    private String lastName;
    private String dob;
    private String address1;
    private String address2;
    private String addressTown;
    private String addressPostcode;

    public Customer(){}

    public Customer(HashMap<String, String> customerDetails) {
        setDetails(customerDetails);
    }

    public HashMap<String,String> getDetails() {
        HashMap<String,String > details = new HashMap<>();
        details.put("customerID",customerID);
        details.put("firstName",firstName);
        details.put("lastName",lastName);
        details.put("dob",dob);
        details.put("address1",address1);
        details.put("address2",address2);
        details.put("addressTown",addressTown);
        details.put("addressPostcode",addressPostcode);
        return details;
    }

    public void setDetails(HashMap<String,String> details) {
        customerID = details.get("customerID");
        firstName = details.get("firstName");
        lastName = details.get("lastName");
        dob = details.get("dob");
        address1 = details.get("address1");
        address2 = details.get("address2");
        addressTown = details.get("addressTown");
        addressPostcode = details.get("addressPostcode");
    }

}
