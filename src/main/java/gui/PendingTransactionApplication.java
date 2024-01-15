package gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import transaction.PendingTransaction;

import java.util.List;

public class PendingTransactionApplication extends Application {
    PendingTransactionController pendingTransactionController;
    AccountController accountController;
    AccountApplication accountApplication;
    List<PendingTransaction> pendingTransactionList;
    Stage currentStage;
    public PendingTransactionApplication(List<PendingTransaction> pendingTransactionList){
        this.pendingTransactionList = pendingTransactionList;
        pendingTransactionController = createPendingTransactionController();
    }

    public PendingTransactionController createPendingTransactionController(){
        return new PendingTransactionController();
    }

    public void setAccountController(AccountController accountController) {
        this.accountController = accountController;
    }

    public void setAccountApplication(AccountApplication accountApplication) {
        this.accountApplication = accountApplication;
    }

    @Override
    public void start(Stage stage) throws Exception {
        pendingTransactionController.setPendingTransactionApplication(this);
        pendingTransactionController.setPendingTransactionList(pendingTransactionList);
        pendingTransactionController.setCurrentStage(stage);
        FXMLLoader fxmlloader =new FXMLLoader();
        fxmlloader.setController(pendingTransactionController);
        fxmlloader.setLocation(getClass().getResource("pendingTransaction-view.fxml"));
        Scene scene = new Scene(fxmlloader.load());
        stage.setTitle("Transactions awaiting authorisation");
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                                    @Override
                                    public void handle(WindowEvent windowEvent) {
                                        close();
                                    }
        });
        stage.showAndWait();
    }

    public void close(){
        currentStage.close();
        accountApplication.reload();
    }


    public void authoriseTransaction(PendingTransaction pendingTransaction){
        int x = pendingTransaction.authorise();
        switch (x){
            case 0: {
                accountController.successDialog(pendingTransaction.getTransactionType());
                pendingTransactionController.updateTable(pendingTransaction);
                break;
            }
            case -1: accountController.unspecifiedFailureDialog(pendingTransaction.getTransactionType()); break;
            case -2: accountController.pendingTransactionsDialog(pendingTransaction.getTransactionType()); break;
            case -3: accountController.overdraftLimitDialog(pendingTransaction.getTransactionType()); break;
        }
    }
}