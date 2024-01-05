package interfaces;

import customer.Customer;
import login.LoginObject;
import transaction.*;

import java.util.HashMap;

public class DataObjectCreator {

    public LoginObject createLoginObject(String username, String password){
        return new LoginObject(username, password);
    }

    public DataObject createPendingAuthorisation(HashMap<String,String> inputMap){
        return new PendingAuthorisation(inputMap);
    }

    public DataObject createNewTransaction(HashMap<String, String> inputMap) {
        return switch (inputMap.get("transactionType")) {
            case "Deposit": yield new DepositTransaction(inputMap);
            case "Withdrawal": yield new WithdrawalTransaction(inputMap);
            case "Transfer": yield new TransferTransaction(inputMap);
            default: yield null;
        };
    }

    public Customer createNewCustomer(HashMap<String,String> inputMap) {
        return new Customer(inputMap);
    }
}
