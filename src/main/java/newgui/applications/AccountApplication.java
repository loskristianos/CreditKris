package newgui.applications;

import newdao.AccountService;
import newdao.PendingTransactionService;
import newdao.SignatoryService;
import newdao.TransactionService;
import newgui.controllers.AccountController;
import newobjects.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.util.List;

public class AccountApplication extends Application {

    Account account;
    Account targetAccount;
    Customer customer;
    List<Transaction> transactionList;
    AccountController accountController;
    AccountService accountService;
    TransactionService transactionService;
    CustomerApplication customerApplication;

    public AccountApplication(Account account, Customer customer){
        this.account = account;
        this.customer = customer;
    }


    @Override
    public void start(Stage stage) throws Exception {

    }
    public void setTargetAccount(Account targetAccount){
        this.targetAccount = targetAccount;
    }

    public int createDeposit(String transactionAmount){
        Transaction deposit = Transaction.createDepositTransaction(new BigDecimal(transactionAmount));
        deposit.setCustomerID(customer.getCustomerID());
        Transaction validatedDeposit = account.validateTransaction(deposit);
        int x = checkTransactionStatus(validatedDeposit);
        if (x==0) {
            saveData(account, validatedDeposit);
        }
        if (x==-2) {createPendingTransactions(validatedDeposit);}
        return x;
    }

    public int createWithdrawal(String transactionAmount){
        Transaction withdrawal = Transaction.createWithdrawalTransaction(new BigDecimal(transactionAmount));
        withdrawal.setCustomerID(customer.getCustomerID());
        Transaction validatedWithdrawal = account.validateTransaction(withdrawal);
        int x = checkTransactionStatus(validatedWithdrawal);
        if (x==0) {
            saveData(account, validatedWithdrawal);
        }
        if (x==-2) {
            createPendingTransactions(validatedWithdrawal);
        }
        return x;
    }

    public int createTransfer(String transactionAmount){
        Transaction transferOut = Transaction.createTransferOutTransaction(new BigDecimal(transactionAmount));
        transferOut.setCustomerID(customer.getCustomerID());
        Transaction validatedTransferOut = account.validateTransaction(transferOut);
        int x = checkTransactionStatus(validatedTransferOut);
        if (x==-2) createPendingTransactions(validatedTransferOut);
        if (x < 0 ) return x;
        Transaction transferIn = Transaction.createTransferInTransaction(new BigDecimal(transactionAmount));
        transferIn.setCustomerID(customer.getCustomerID());
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

    void createPendingTransactions(Transaction transaction){
        SignatoryService signatoryService = SignatoryService.getSignatoryService();
        PendingTransactionService pendingService = PendingTransactionService.getPendingTransactionService();
        List<Signatory> sigList = signatoryService.getAccountSignatories(account);
        for (Signatory signatory : sigList) {
            PendingTransaction newPending = PendingTransaction.createPendingTransaction(transaction, signatory.getCustomerID());
            pendingService.create(newPending);
        }
    }

    void saveData(Account account, Transaction transaction){
        accountService = AccountService.getAccountService();
        transactionService = TransactionService.getTransactionService();
        accountService.update(account);
        transactionService.create(transaction);
    }

    public void goBack(){
        // reload previous scene in current stage
    }
}
