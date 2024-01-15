package account;

import customer.Customer;

import java.util.HashMap;

public class SmallBusinessAccount extends Account {
    final String overdraftLimit = "1000.00";
    final String accountType = "Business";

    public SmallBusinessAccount(Customer customer){
        super(customer);
        setOverdraftLimit(this.overdraftLimit);
        setAccountType(this.accountType);
    }
    public SmallBusinessAccount(HashMap<String,String> accountDetails) {
        super(accountDetails);
        setOverdraftLimit(this.overdraftLimit);
        setAccountType(this.accountType);
    }
}
