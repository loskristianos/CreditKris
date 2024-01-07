package dao;

import login.LoginObject;

import java.util.HashMap;
import java.util.List;

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
        List<HashMap<String,String>> resultList = super.databaseLookup();
        login.setCustomerID(resultList.getFirst().get("customerID"));
        return login;
    }
}
