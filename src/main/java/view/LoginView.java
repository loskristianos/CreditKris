package view;

import interfaces.DataObject;
import interfaces.DataObjectCreator;

import java.util.HashMap;

public class LoginView extends View {
    String username;
    String password;
    DataObjectCreator objectCreator;
    @Override
    View displayView() {
        return null;
    }

    @Override
    HashMap<String, String> getViewFields() {
        HashMap<String,String> outputMap = new HashMap<>();
        outputMap.put("username",username);
        outputMap.put("password",password);
        return outputMap;
    }

    @Override
    DataObject createObject(HashMap<String, String> inputMap) {
        DataObject outputObject = objectCreator.createLoginObject(username,password);
        return outputObject;
    }
}
