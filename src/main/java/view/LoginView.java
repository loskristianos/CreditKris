package view;
import cli.*;
import interfaces.DataObject;
import interfaces.DataObjectCreator;
import interfaces.UI;

import java.util.HashMap;

public class LoginView extends View {
    UI uitype;
    String username;
    String password;
    DataObjectCreator objectCreator;


    public LoginView() {
        this.uitype= new LoginPrompt();
    }

    @Override
    public void displayView() {
        // launch login prompt
        uitype.displayPrompt();

    }

    @Override
    public String getSelectedOption() {
        return null;
    }

    @Override
    public HashMap<String, String> getViewFields() {
        return uitype.getInputDetails();
    }

    @Override
    public DataObject createObject(HashMap<String, String> inputMap) {
        DataObject outputObject = objectCreator.createLoginObject(username,password);
        return outputObject;
    }
}
