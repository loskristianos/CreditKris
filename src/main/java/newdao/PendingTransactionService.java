package newdao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import newobjects.PendingTransaction;

import java.sql.SQLException;
import java.util.List;

public class PendingTransactionService {

    private String dbUrl;
    private JdbcPooledConnectionSource pooledConnectionSource;

    private PendingTransactionService(){
    }

    public static PendingTransactionService getPendingTransactionService(){
        return new PendingTransactionService();
    }

    private ConnectionSource getConnection()throws SQLException {
        return new JdbcPooledConnectionSource(dbUrl);
    }

    public List<PendingTransaction> getPendingTransactions(){
        return null;
    }

    public void create(PendingTransaction transaction){
        try(ConnectionSource connection = getConnection()){
            Dao<PendingTransaction, String> pendingDao = DaoManager.createDao(connection, PendingTransaction.class);
            pendingDao.create(transaction);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
