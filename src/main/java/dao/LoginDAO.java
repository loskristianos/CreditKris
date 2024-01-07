package dao;

import login.LoginObject;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;

public class LoginDAO extends DAO{
    LoginObject login;
    public LoginDAO(LoginObject inputObject) {
        super(inputObject);
        this.login = inputObject;
    }

    @Override
    public void write() {
        this.tableName = "login";
        this.sqlStatement = super.prepareInsertStatement();
        super.write();
    }

    public LoginObject getLogin(){
        String username = login.getUsername();
        String password = login.getPassword();
        sqlStatement = "SELECT * FROM login WHERE username = '" + username + "' AND password = '" + password +"'";
        try (CachedRowSet resultSet = super.databaseLookup()) {
            if (resultSet.size() == 1) {
                login.setCustomerID(resultSet.getString("customer_id"));
                return login;
            }
            else return null;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
