package dao;

import account.Account;
import account.Signatory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SignatoryDAO extends DAO{
    Signatory signatory;
    Account account;
    public SignatoryDAO(Signatory signatory) {
        super(signatory);
        this.signatory = signatory;
    }

    public SignatoryDAO(Account account) {
        super(account);
        this.account = account;
    }

    public void write(){
        tableName = "signatories";
        sqlStatement = super.prepareInsertStatement();
        super.write();
    }

    public List<Signatory> getSignatories(){
        String accountNumber = account.getAccountNumber();
        List<Signatory> signatoryList = new ArrayList<>();
        sqlStatement = "SELECT * FROM signatories WHERE account_number = '" + accountNumber + "'";
        List<HashMap<String,String>> resultList = super.databaseLookup();
        for (HashMap<String,String> map : resultList) {
            Signatory resultSignatory = new Signatory(map);
            signatoryList.add(resultSignatory);
        }
        return signatoryList;
    }
}
