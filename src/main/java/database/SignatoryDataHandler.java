package database;

import interfaces.DataObject;

import java.util.ArrayList;

public class SignatoryDataHandler extends DataHandler {
    ArrayList<DataObject> inputList;

    public SignatoryDataHandler(ArrayList<DataObject> inputObjects){
        super();
        this.inputList = inputObjects;
    }

    public SignatoryDataHandler(DataObject inputObject) {
        super(inputObject);
    }

    public void writeNewRecord(){
        this.tableName = "signatories";
        super.writeNewRecord();
    }

    public void writeAllRecords(){
        for (DataObject dataObject : inputList) {
           new SignatoryDataHandler(dataObject).writeNewRecord();
        }
    }

    @Override
    public void update() {
        // not required for this object
    }
}
