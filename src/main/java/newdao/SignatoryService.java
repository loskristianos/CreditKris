package newdao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import newobjects.Signatory;
import newobjects.Account;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

public class SignatoryService {

    private String dbUrl;
    private JdbcPooledConnectionSource pooledConnectionSource;

    private SignatoryService(){
    }

    public static SignatoryService getSignatoryService(){
        return new SignatoryService();
    }

    private ConnectionSource getConnection() throws SQLException {
        return new JdbcPooledConnectionSource(dbUrl);
    }

    public List<Signatory> getAccountSignatories(Account account) {
        try(ConnectionSource connection = getConnection()) {
            Dao<Signatory, Integer> signatoryDao = DaoManager.createDao(connection, Signatory.class);
            return signatoryDao.queryForEq("AccountNumber", account.getAccountNumber());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void create(Signatory signatory){
        try(ConnectionSource connection = getConnection()){
            Dao<Signatory,Integer> signatoryDao = DaoManager.createDao(connection, Signatory.class);
            signatoryDao.create(signatory);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }




}
