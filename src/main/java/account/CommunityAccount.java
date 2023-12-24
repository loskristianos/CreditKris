package account;


import java.util.HashMap;

public class CommunityAccount extends Account {

    final String overdraftLimit = "2500";
    final String accountType = "community";

    public CommunityAccount() {
        super();
    }

    public CommunityAccount(HashMap<String,String> accountDetails) {
        super(accountDetails);
        setOverdraftLimit(this.overdraftLimit);
        setAccountType(this.accountType);
    }

}
