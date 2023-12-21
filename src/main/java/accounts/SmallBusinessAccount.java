package accounts;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable (tableName = "small_business_account")
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
