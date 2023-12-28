package interfaces;

import login.LoginObject;
import transaction.PendingAuthorisation;

import java.util.HashMap;

public class DataObjectCreator implements DataObject {

    public DataObject createLoginObject(String username, String password){
        return new LoginObject(username, password);
    }

    public DataObject createPendingAuthorisation(HashMap<String,String> inputMap){
        return new PendingAuthorisation(inputMap);
    }
    @Override
    public HashMap<String, String> getDetails() {
        return null;
    }

    @Override
    public void setDetails(HashMap<String, String> details) {

    }
}
