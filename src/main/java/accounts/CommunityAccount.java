package accounts;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable (tableName = "community_account")
public class CommunityAccount extends Account {

    final String overdraftLimit = "2500";

    public CommunityAccount() {
        super();
    }

    public CommunityAccount(String[] accountDetails) {
        super(accountDetails);
        setOverdraftLimit(this.overdraftLimit);
    }

}
