package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import transaction.PendingTransaction;

import java.util.List;

public class PendingTransactionApplication extends Application {
    PendingTransactionController pendingTransactionController;
    List<PendingTransaction> pendingTransactionList;
    public PendingTransactionApplication(List<PendingTransaction> pendingTransactionList){
        this.pendingTransactionList = pendingTransactionList;
        pendingTransactionController = createPendingTransactionController();
    }

    public PendingTransactionController createPendingTransactionController(){
        return new PendingTransactionController();
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
        stage.showAndWait();

    }

    public void selectPendingTransaction(PendingTransaction pendingTransaction){
       int x = pendingTransactionController.confirmAuthorisationDialog(pendingTransaction);
       if (x==0) pendingTransaction.authorise();
    }
}