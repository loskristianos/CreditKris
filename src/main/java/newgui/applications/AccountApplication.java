package newgui.applications;

import newdao.AccountDao;
import newdao.AccountDaoImpl;
import newdao.TransactionDaoImpl;
import newobjects.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.sql.SQLException;

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
            saveData(account, validatedDeposit);
        }
        return x;
    }

    public int createWithdrawal(String transactionAmount){
        Transaction withdrawal = Transaction.createWithdrawalTransaction(new BigDecimal(transactionAmount));
        Transaction validatedWithdrawal = account.validateTransaction(withdrawal);
        int x = checkTransactionStatus(validatedWithdrawal);
        if (x==0) {
            saveData(account, validatedWithdrawal);
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
            saveData(account, validatedTransferOut);
            saveData(targetAccount, validatedTransferIn);
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

    void saveData(Account account, Transaction transaction){
        try {
            AccountDaoImpl accountDao = AccountDaoImpl.getAccountDao();
            TransactionDaoImpl transactionDao = TransactionDaoImpl.getTransactionDao();
            transactionDao.create(transaction);
            accountDao.update(account);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            AccountDaoImpl.closeConnection();
            TransactionDaoImpl.closeConnection();
        }
    }

    public void goBack(){
        // reload previous scene in current stage
    }
}
