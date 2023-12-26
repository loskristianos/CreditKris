package account;

import java.util.HashMap;

public class SmallBusinessAccount extends Account {

    final String overdraftLimit = "1000";
    final String accountType = "business";

    public SmallBusinessAccount() {
        super();
    }

    public SmallBusinessAccount(HashMap<String,String> accountDetails) {
        super(accountDetails);
        setOverdraftLimit(this.overdraftLimit);
        setAccountType(this.accountType);
    }

}