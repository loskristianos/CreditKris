package newdao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import newobjects.Transaction;

import java.sql.SQLException;

public class TransactionDaoImpl extends BaseDaoImpl<Transaction, Integer> implements TransactionDao {

    private static String dbUrl;
    private static JdbcPooledConnectionSource pooledConnectionSource;

    public TransactionDaoImpl(ConnectionSource connectionsource) throws SQLException {
        super(connectionsource, Transaction.class);
    }

    public static TransactionDaoImpl getTransactionDao() throws SQLException {
        pooledConnectionSource = new JdbcPooledConnectionSource(dbUrl);
        return new TransactionDaoImpl(pooledConnectionSource);
    }

    public static void closeConnection() {
        try {
            pooledConnectionSource.close();
        } catch (Exception ignored) {
        }
    }
}
