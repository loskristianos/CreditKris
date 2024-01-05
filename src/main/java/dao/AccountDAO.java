package dao;

import account.Account;

public class AccountDAO extends DAO{
    // fields

    // constructors
    public AccountDAO(Account account){
        super(account);
    }

    // methods


    @Override
    public void write() {
        this.tableName = "accounts";
        super.write();
    }
}
