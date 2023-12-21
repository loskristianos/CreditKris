package account;


public class ClientAccount extends Account {

    final String overdraftLimit = "1500";

    public ClientAccount(){
        super();
    }

    public ClientAccount(String[] accountDetails) {
        super(accountDetails);
        setOverdraftLimit(this.overdraftLimit);
    }

}



