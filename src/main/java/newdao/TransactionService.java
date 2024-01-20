package newdao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import newobjects.Account;
import newobjects.Transaction;

import java.sql.SQLException;
import java.util.List;

public class TransactionService {

    private String dbUrl;
    private JdbcPooledConnectionSource pooledConnectionSource;

    private TransactionService(){
    }

    public static TransactionService getTransactionService(){
        return new TransactionService();
    }

    private ConnectionSource getConnection() throws SQLException {
        return new JdbcPooledConnectionSource(dbUrl);
    }

    public List<Transaction> getTransactions(Account account){
        try (ConnectionSource connection = getConnection()){
            Dao<Transaction, String> transactionDao = DaoManager.createDao(connection, Transaction.class);
            return transactionDao.queryForEq("accountNumber", account.getAccountNumber());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void create(Transaction transaction){
        try (ConnectionSource connection = getConnection()){
            Dao<Transaction, String> transactionDao = DaoManager.createDao(connection, Transaction.class);
            transactionDao.create(transaction);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
