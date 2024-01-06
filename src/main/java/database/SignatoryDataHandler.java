package database;

import interfaces.DataObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SignatoryDataHandler extends DataHandler {
    List<DataObject> inputList;

    public SignatoryDataHandler(List<DataObject> inputList){
        super();
        this.inputList = inputList;
    }

    public SignatoryDataHandler(DataObject inputObject) {
        super(inputObject);
    }

    public void writeNewRecord() {
        this.tableName = "signatories";
        super.writeNewRecord();
    }

    public void writeAllRecords(){
        this.tableName = "signatories";
        for (DataObject dataObject : inputList) {
            this.inputObject = dataObject;
            super.writeNewRecord();
        }
    }

    public List<DataObject> getRecords() {      // get list of signatories from an account for a transaction
        HashMap<String,String> transactionDetails = inputObject.getDetails();
        String accountNumber = transactionDetails.get("accountNumber");
        this.readQuery = "SELECT * FROM signatories WHERE account_number = '" + accountNumber + "'";
        this.resultList = new ArrayList<>();
        this.outputType = "Signatory";
        return super.getRecords();
    }
}
