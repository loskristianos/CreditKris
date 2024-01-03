package view;

import cli.AccountsPrompt;
import interfaces.DataObject;
import interfaces.UI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AccountsView extends View{
    List<DataObject> accountList;
    List<HashMap<String,String>> accountDetailsList;
    UI uiType;

    public AccountsView(List<DataObject> inputList, DataObject customer){
        this.accountList = inputList;
        this.accountDetailsList = new ArrayList<>();
        for (DataObject object : inputList) {
            accountDetailsList.add(object.getDetails());
        }
        this.uiType = new AccountsPrompt(accountDetailsList,customer.getDetails());
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
