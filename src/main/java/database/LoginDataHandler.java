package database;

import interfaces.DataObject;
import login.LoginObject;

import java.util.ArrayList;
import java.util.List;

public class LoginDataHandler extends DataHandler {

    public LoginDataHandler(LoginObject login){
        super(login);
    }

    public void writeNewRecord() {
        this.tableName = "login";
        super.writeNewRecord();
    }

    public List<DataObject> getRecords() {
        // return records matching username and password - will only ever be one or none
        String username = inputObject.getDetails().get("username");
        String password = inputObject.getDetails().get("password");
        this.readQuery = "SELECT * FROM login WHERE username = '" + username + "' AND password = '" + password +"'";
        this.resultList = new ArrayList<>();
        this.outputType = "Login";
        return super.getRecords();
    }

}
