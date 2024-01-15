package account;


import customer.Customer;

import java.util.HashMap;

public class CommunityAccount extends Account {
    final String overdraftLimit = "2500.00";
    final String accountType = "Community";

    public CommunityAccount (Customer customer){
        super(customer);
        setOverdraftLimit(this.overdraftLimit);
        setAccountType(this.accountType);
    }
    public CommunityAccount(HashMap<String,String> accountDetails) {
        super(accountDetails);
        setOverdraftLimit(this.overdraftLimit);
        setAccountType(this.accountType);
    }

}
