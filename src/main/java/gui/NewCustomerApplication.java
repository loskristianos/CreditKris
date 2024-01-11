package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NewCustomerApplication extends Application {

    NewCustomerController newCustomerController;
    Stage currentStage;

    public NewCustomerApplication(){
        newCustomerController = createNewCustomerController();
        newCustomerController.setNewCustomerApplication(this);
    }
    private NewCustomerController createNewCustomerController(){
        return new NewCustomerController();
    }
    @Override
    public void start(Stage stage) throws Exception {
        newCustomerController.setCurrentStage(stage);
        currentStage = stage;
        FXMLLoader fxmlloader =new FXMLLoader();
        fxmlloader.setController(newCustomerController);
        fxmlloader.setLocation(getClass().getResource("newCustomer-view.fxml"));
        Scene scene = new Scene(fxmlloader.load());
        stage.setTitle("Enter Customer Details");
        stage.setScene(scene);
    }
}
