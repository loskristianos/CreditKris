package accounts;

public class CommunityAccount extends Account {

    double overdraftLimit = 2500;

    public CommunityAccount(String[] accountDetails, double currentBalance) {
        super(accountDetails, currentBalance);
    }

}
