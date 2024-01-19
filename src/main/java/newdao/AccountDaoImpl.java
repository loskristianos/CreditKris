package newdao;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import newobjects.Account;
import com.j256.ormlite.dao.BaseDaoImpl;

import java.sql.SQLException;

public class AccountDaoImpl extends BaseDaoImpl<Account, Integer> implements AccountDao {

    private static String dbUrl;
    private static JdbcPooledConnectionSource pooledConnectionSource;

    public AccountDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Account.class);
    }

    public static AccountDaoImpl getAccountDao () throws SQLException{
        pooledConnectionSource = new JdbcPooledConnectionSource(dbUrl);
        return new AccountDaoImpl(pooledConnectionSource);
    }

    public static void closeConnection(){
        try {pooledConnectionSource.close();}
        catch (Exception ignored) {
        }

    }
}
