package interfaces;

import login.LoginObject;
import transaction.*;

import java.util.HashMap;

public class DataObjectCreator implements DataObject {

    public DataObject createLoginObject(String username, String password){
        return new LoginObject(username, password);
    }

    public DataObject createPendingAuthorisation(HashMap<String,String> inputMap){
        return new PendingAuthorisation(inputMap);
    }

    public DataObject createNewTransaction(HashMap<String, String> inputMap) {
        Transaction newTransaction =
        switch (inputMap.get("transactionType")) {
            case "Deposit": yield new DepositTransaction(inputMap);
            case "Withdrawal": yield new WithdrawalTransaction(inputMap);
            case "Transfer": yield new TransferTransaction(inputMap);
            default: yield null;
        };
        return newTransaction;
    }

    @Override
    public HashMap<String, String> getDetails() {
        return null;
    }

    @Override
    public void setDetails(HashMap<String, String> details) {

    }
}
