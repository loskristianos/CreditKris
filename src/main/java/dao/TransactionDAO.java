package dao;

import account.Account;
import transaction.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TransactionDAO extends DAO{
    Transaction transaction;
    Account account;
    public TransactionDAO(Transaction inputTransaction) {
        super(inputTransaction);
        this.transaction = inputTransaction;
    }

    public TransactionDAO(Account account) {
        super(account);
        this.account = account;
    }

    public void write(){
        tableName = "transactions";
        sqlStatement = super.prepareInsertStatement();
        super.write();
    }

    public List<Transaction> getTransactions() {
        List<Transaction> transactionList = new ArrayList<>();
        String accountNumber = account.getAccountNumber();
        sqlStatement = "SELECT * FROM transactions WHERE account_number =  '" + accountNumber + "'";
        List<HashMap<String,String>> resultList = super.databaseLookup();
        for (HashMap<String,String> map : resultList) {
            Transaction returnedTransaction = switch (map.get("transactionType")){
                case "Deposit": yield new DepositTransaction(map);
                case "Withdrawal": yield new WithdrawalTransaction(map);
                case "Transfer In","Transfer Out": yield new TransferTransaction(map);
                default: yield null;
            };
            transactionList.add(returnedTransaction);
        }
        return transactionList;
    }

    public Transaction getThisTransaction(){
        String transactionID = transaction.getTransactionID();
        sqlStatement = "SELECT * FROM transactions WHERE transaction_id = '" + transactionID + "'";
        HashMap<String,String> returnMap = super.databaseLookup().getFirst();
        return switch(returnMap.get("transactionType")){
            case "Deposit": yield new DepositTransaction(returnMap);
            case "Withdrawal": yield new WithdrawalTransaction(returnMap);
            case "Transfer In","Transfer Out": yield new TransferTransaction(returnMap);
            default:yield null;
        };
    }
}
