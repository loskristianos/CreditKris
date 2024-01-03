package view;

import cli.TransactionsPrompt;
import interfaces.DataObject;
import interfaces.UI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TransactionsView extends View{

    List<DataObject> transactionsList;
    List<HashMap<String,String>> transactionDetailsList;
    UI uiType;

    public TransactionsView(List<DataObject> inputList,DataObject account) {
        this.transactionsList = inputList;
        this.transactionDetailsList = new ArrayList<>();
        for (DataObject object : inputList) {
            transactionDetailsList.add(object.getDetails());
        }
        this.uiType = new TransactionsPrompt(transactionDetailsList,account.getDetails());
    }
    @Override
    public void displayView() {
        uiType.displayPrompt();
    }

    @Override
    public String getSelectedOption() {
        return uiType.optionSelection();
    }

    @Override
    public HashMap<String, String> getViewFields() {
        return null;
    }

    @Override
    public DataObject createObject(HashMap<String, String> inputMap) {
        return null;
    }
}
