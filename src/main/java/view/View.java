package view;

import interfaces.DataObject;

import java.util.HashMap;
import java.util.List;

public abstract class View {

    // variable to set UI type set at main method

    // constructors for Views
    public View() {}
    public View(DataObject inputObject) {}
    public View(List<DataObject> inputList){}

    // methods for all views

   public abstract void displayView();
    /* for javafx create/display the stage, for ConsoleUI build the prompts etc
            returns this.view.View(); */

    public abstract String getSelectedOption();
 //   abstract void prepareViewFieldsFromObject();
 //   abstract void prepareViewFieldsFromList();

    public abstract HashMap<String, String> getViewFields();
        /* create a hashMap from the input fields - (ConsoleUI does this already I think) */

    public abstract DataObject createObject(HashMap<String,String> inputMap);
       /* create a DataObject from the HashMap - might already be covered by DataObjectCreator */
}