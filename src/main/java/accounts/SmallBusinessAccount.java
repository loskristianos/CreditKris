package accounts;

public class SmallBusinessAccount extends Account {

    final String overdraftLimit = "1000";

    public SmallBusinessAccount() {
        super();
    }

    public SmallBusinessAccount(String[] accountDetails) {
        super(accountDetails);
        setOverdraftLimit(this.overdraftLimit);
    }

}
