package account;


import java.util.HashMap;

public class ClientAccount extends Account {

    final String overdraftLimit = "1500";
    final String accountType = "Client";

    public ClientAccount(){
        super();
        setOverdraftLimit(this.overdraftLimit);
        setAccountType(this.accountType);
    }

    public ClientAccount(HashMap<String,String> accountDetails) {
        super(accountDetails);
        setOverdraftLimit(this.overdraftLimit);
        setAccountType(this.accountType);
    }

}



