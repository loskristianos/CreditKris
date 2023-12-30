package account;


import java.util.HashMap;

public class ClientAccount extends Account {

    final String overdraftLimit = "1500";
    final String accountType = "Client";

    public ClientAccount(){
        super();
    }

    public ClientAccount(HashMap<String,String> accountDetails) {
        super(accountDetails);
        setOverdraftLimit(this.overdraftLimit);
        setAccountType(this.accountType);
    }

}



