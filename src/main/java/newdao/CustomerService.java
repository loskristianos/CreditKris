package newdao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import newobjects.Customer;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class CustomerService {

    private String dbUrl;
    private JdbcPooledConnectionSource pooledConnectionSource;

    private CustomerService(){
    }

    public static CustomerService getCustomerService(){
        return new CustomerService();
    }

    private ConnectionSource getConnection() throws SQLException {
        return new JdbcPooledConnectionSource(dbUrl);
    }

    public Customer getLogin(String username, String password){
        HashMap<String,Object> loginDetails = new HashMap<>();
        loginDetails.put("username",username); loginDetails.put("password",password);
        try (ConnectionSource connection = getConnection()) {
            Dao<Customer, Integer> customerDao = DaoManager.createDao(connection, Customer.class);
            List<Customer> returnList = customerDao.queryForFieldValues(loginDetails);
            if (returnList != null && !returnList.isEmpty()) {
                return returnList.getFirst();
            } else return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
