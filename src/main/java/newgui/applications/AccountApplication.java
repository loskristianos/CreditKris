package newgui.applications;

import newobjects.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.math.BigDecimal;

public class AccountApplication extends Application {

    Account account;
    Account targetAccount;


    @Override
    public void start(Stage stage) throws Exception {

    }
    public void setTargetAccount(Account targetAccount){
        this.targetAccount = targetAccount;
    }

    public int createDeposit(String transactionAmount){
        Transaction deposit = Transaction.createDepositTransaction(new BigDecimal(transactionAmount));
        Transaction validatedDeposit = account.validateTransaction(deposit);
        int x = checkTransactionStatus(validatedDeposit);
        if (x==0) {
            // persist account with ormlite DAO
            // persist transaction with ormlite DAO
        }
        return x;
    }

    public int createWithdrawal(String transactionAmount){
        Transaction withdrawal = Transaction.createWithdrawalTransaction(new BigDecimal(transactionAmount));
        Transaction validatedWithdrawal = account.validateTransaction(withdrawal);
        int x = checkTransactionStatus(validatedWithdrawal);
        if (x==0) {
            // persist account with ormlite DAO
            // persist validatedWithdrawal with ormlite DAO
        }
        return x;
    }

    public int createTransfer(String transactionAmount){
        Transaction transferOut = Transaction.createTransferOutTransaction(new BigDecimal(transactionAmount));
        Transaction validatedTransferOut = account.validateTransaction(transferOut);
        int x = checkTransactionStatus(validatedTransferOut);
        if (x < 0 ) return x;
        Transaction transferIn = Transaction.createTransferInTransaction(new BigDecimal(transactionAmount));
        Transaction validatedTransferIn = account.validateTransaction(transferIn);
        int y = checkTransactionStatus(validatedTransferIn);
        if (y == 0) {
            // persist account with ormlite DAO
            // persist validatedTransferOut with ormlite DAO
            // persist targetAccount with ormlite DAO
            // persist validatedTransferIn with ormlite DAO
        }
        return y;
    }

    int checkTransactionStatus(Transaction transaction){
        switch (transaction.getTransactionStatus()){
            case COMPLETE -> {return 0;}
            case FAILED_BALANCE -> {return -3;}
            case REQUIRES_AUTHORISATION -> {return -2;}
            default -> {return -1;}
        }
    }
}
