package view;

import interfaces.DataObject;

import java.util.HashMap;
import java.util.List;

public abstract class View {

    public View() {}

    public View(DataObject inputObject) {}
    public View(List<DataObject> inputList){}
    abstract View displayView();
        /* for javafx create/display the stage, for ConsoleUI build the prompts etc
            returns this.view.View(); */

    abstract HashMap<String, String> getViewFields();
        /* create a hashMap from the input fields - (ConsoleUI does this already I think) */

    abstract DataObject createObject(HashMap<String,String> inputMap);
       /* create a DataObject from the HashMap - might already be covered by DataObjectCreator */


}