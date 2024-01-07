package dao;

import account.Account;
import account.ClientAccount;
import account.CommunityAccount;
import account.SmallBusinessAccount;
import customer.Customer;
import database.MapFieldsToColumns;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
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
        List<Account> resultList = new ArrayList<>();
        String customerID = customer.getCustomerID();
        sqlStatement = "SELECT * FROM accounts WHERE account_number IN (SELECT account_number FROM accounts WHERE customer_id = " + customerID + " UNION SELECT account_number FROM signatories WHERE customer_id = " + customerID + ")";
        try (CachedRowSet resultSet = super.databaseLookup()) {
            HashMap<String, String> outputMap = new HashMap<>();
            while (resultSet.next()) {
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    String key = resultSet.getMetaData().getColumnName(i);
                    String mappedKey = MapFieldsToColumns.mappingsFromDB.get(key);
                    String value = resultSet.getString(i);
                    outputMap.put(mappedKey, value);
                }
                Account resultAccount = switch (outputMap.get("accountType")) {
                    case "Client":
                        yield new ClientAccount(outputMap);
                    case "Business":
                        yield new SmallBusinessAccount(outputMap);
                    case "Community":
                        yield new CommunityAccount(outputMap);
                    default:
                        yield null;
                };
                resultList.add(resultAccount);
            }
            return resultList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
