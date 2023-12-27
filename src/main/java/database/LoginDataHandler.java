package database;

import interfaces.DataObject;
import login.LoginObject;

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
        return null;
    }

    public void update() {
        // not required yet
    }
}
