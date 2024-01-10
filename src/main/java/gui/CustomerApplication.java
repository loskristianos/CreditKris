package gui;

import customer.Customer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CustomerApplication extends Application {
    Customer customer;
    CustomerController customerController;

    public CustomerApplication(Customer customer){
        this.customer = customer;
        this.customerController = new CustomerController();
        customerController.setCustomer(customer);
    }

    @Override
    public void start(Stage stage) throws Exception {
        customerController.setCurrentStage(stage);
        FXMLLoader fxmlloader =new FXMLLoader();
        fxmlloader.setController(customerController);
        fxmlloader.setLocation(getClass().getResource("customer-view.fxml"));
        Scene scene = new Scene(fxmlloader.load());
        customerController.setPreviousScene(scene);
        stage.setTitle("Customer Details");
        stage.setScene(scene);
    }
}
