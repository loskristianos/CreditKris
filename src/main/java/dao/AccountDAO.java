package dao;

import account.Account;
import account.ClientAccount;
import account.CommunityAccount;
import account.SmallBusinessAccount;
import customer.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class AccountDAO extends DAO{
    String accountNumber;
    String balance;
    Customer customer;
    public AccountDAO(Account inputAccount) {
        super(inputAccount);
        accountNumber = inputAccount.getAccountNumber();
        balance = inputAccount.getCurrentBalance();
    }

    public AccountDAO(Customer customer) {
        super(customer);
        this.customer = customer;
    }

    @Override
    public void write() {
        tableName = "accounts";
        sqlStatement = super.prepareInsertStatement();
        super.write();
    }

    public void update() {
        sqlStatement = "UPDATE accounts SET current_balance = '" + balance + "' WHERE account_number = '" + accountNumber + "'";
        super.update();
    }

    public List<Account> getAccounts() {
        List<Account> accountList = new ArrayList<>();
        String customerID = customer.getCustomerID();
        sqlStatement = "SELECT * FROM accounts WHERE account_number IN (SELECT account_number FROM accounts WHERE customer_id = " + customerID + " UNION SELECT account_number FROM signatories WHERE customer_id = " + customerID + ")";
        List<HashMap<String,String>> resultList = super.databaseLookup();
        for (HashMap<String,String> map : resultList) {
            Account resultAccount = switch (map.get("accountType")) {
                case "Client":
                    yield new ClientAccount(map);
                case "Business":
                    yield new SmallBusinessAccount(map);
                case "Community":
                    yield new CommunityAccount(map);
                default:
                    yield null;
            };
            accountList.add(resultAccount);
        }
        return accountList;
    }
}
