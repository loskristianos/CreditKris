package newdao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import newobjects.Account;
import newobjects.Customer;
import newobjects.Signatory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountService {
    private Dao<Account, Integer> accountDao;
    private Dao<Signatory, Integer> signatoryDao;
    private String dbUrl;
    private JdbcPooledConnectionSource pooledConnectionSource;

    private AccountService(){

    }

    public static AccountService getAccountService(){
        return new AccountService();
    }

    private ConnectionSource getConnection() throws SQLException {
        return new JdbcPooledConnectionSource(dbUrl);
    }

    public List<Account> getAccounts(Customer customer){
        try (ConnectionSource connection = getConnection()) {
            signatoryDao = DaoManager.createDao(connection, Signatory.class);
            List<Signatory> signatoryList = signatoryDao.queryForEq("customerID", customer.getCustomerID());
            accountDao = DaoManager.createDao(connection, Account.class);
            List<Account> accountList = new ArrayList<>();
            for(Signatory signatory : signatoryList){
                Account account = accountDao.queryForId(signatory.getAccountNumber());
                accountList.add(account);
            }
            return accountList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Account getThisAccount(Account account){
        try (ConnectionSource connection = getConnection()) {
            accountDao = DaoManager.createDao(connection, Account.class);
            List<Account> results = accountDao.queryForMatching(account);
            if (results != null) return results.getFirst();
            else return null;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void update(Account account){
        try (ConnectionSource connection = getConnection()){
            accountDao = DaoManager.createDao(connection, Account.class);
            accountDao.update(account);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void create(Account account) {
        try (ConnectionSource connection = getConnection()) {
            accountDao = DaoManager.createDao(connection, Account.class);
            accountDao.create(account);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
