package accounts;

public class SmallBusinessAccount extends Account {

    String overdraftLimit = "1000";

    public SmallBusinessAccount(String[] accountDetails, double currentBalance) {
        super(accountDetails, currentBalance);
    }
}
