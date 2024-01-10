package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NewCustomerApplication extends Application {

    NewCustomerController newCustomerController;

    public NewCustomerApplication(){
        this.newCustomerController = new NewCustomerController();
    }
    @Override
    public void start(Stage stage) throws Exception {
        newCustomerController.setCurrentStage(stage);
        FXMLLoader fxmlloader =new FXMLLoader();
        fxmlloader.setController(newCustomerController);
        fxmlloader.setLocation(getClass().getResource("newCustomer-view.fxml"));
        Scene scene = new Scene(fxmlloader.load());
        stage.setTitle("Enter Customer Details");
        stage.setScene(scene);
    }
}
