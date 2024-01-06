package dao;

import account.Account;

public class AccountDAO extends DAO{
    String accountNumber;
    String balance;
    public AccountDAO(Account inputAccount) {
        super(inputAccount);
        this.accountNumber = inputAccount.getAccountNumber();
        this.balance = inputAccount.getCurrentBalance();
    }

    @Override
    public void write() {
        this.tableName = "accounts";
        this.sqlStatement = super.prepareInputStatement();
        super.write();
    }

    public void update() {
        this.sqlStatement = "UPDATE accounts SET current_balance = '" + balance + "' WHERE account_number = '" + accountNumber + "'";
        super.update();
    }
}
