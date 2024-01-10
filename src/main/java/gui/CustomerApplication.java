package gui;

import customer.Customer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CustomerApplication extends Application {
    Customer customer;
    CustomerController customerController;
    Scene previousScene;

    public CustomerApplication(Customer customer){
        this.customer = customer;
        this.customerController = new CustomerController();
        customerController.setCustomer(customer);
    }
    public void setPreviousScene(Scene scene) {
        previousScene = scene;
    }
    @Override
    public void start(Stage stage) throws Exception {
        customerController.setCurrentStage(stage);
        customerController.setPreviousScene(previousScene);
        FXMLLoader fxmlloader =new FXMLLoader();
        fxmlloader.setController(customerController);
        fxmlloader.setLocation(getClass().getResource("customer-view.fxml"));
        Scene scene = new Scene(fxmlloader.load());
        stage.setTitle("Customer Details");
        stage.setScene(scene);
    }
}
