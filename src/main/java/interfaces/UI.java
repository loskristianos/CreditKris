package interfaces;

import java.util.HashMap;

public interface UI {

    void displayPrompt();

    HashMap<String,String> getInputDetails();

    String optionSelection();
}
