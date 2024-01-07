package customer;

import account.Account;
import dao.AccountDAO;
import dao.PendingTransactionDAO;
import interfaces.DataObject;
import login.LoginObject;
import dao.CustomerDAO;
import transaction.PendingTransaction;

import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;

public class Customer implements DataObject {
    LoginObject login;
    private String customerID;
    private String firstName;
    private String lastName;
    private String dob;
    private String address1;
    private String address2;
    private String addressTown;
    private String addressPostcode;

    public Customer(){}

    public Customer(LoginObject login){
        this.login = login;
    }

    public Customer(HashMap<String, String> customerDetails) {
        setDetails(customerDetails);
    }

    public String getCustomerID() {
        return customerID;
    }
    public void setCustomerID(String customerID){
        this.customerID = customerID;
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

    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getFullName(){
        return firstName + " " + lastName;
    }
    public String getDob(){return dob;}
    public String getFullAddress(){
        StringJoiner fullAddress = new StringJoiner("\n");
        fullAddress.add(address1);
        if(address2 != null) fullAddress.add(address2);
        fullAddress.add(addressTown);
        fullAddress.add(addressPostcode);
        return fullAddress.toString();
    }

    public void write(){
        new CustomerDAO(this).write();
    }

    public List<Account> getAccounts(){
        return new AccountDAO(this).getAccounts();
    }

    public List<PendingTransaction> getPendingTransactions(){
        return new PendingTransactionDAO(this).getCustomerPendingTransactions();
    }
}