package gui;

import customer.Customer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import login.LoginObject;


public class LoginApplication extends Application {

    LoginController loginController;
    Stage currentStage;

    public LoginApplication(){
        loginController = createLoginController();
        loginController.setLoginApplication(this);
    }

    public void displayScreen(){
        launch();
    }

    LoginController createLoginController(){
        return new LoginController();
    }
    LoginObject createLoginObject(String username, String password){
        return new LoginObject(username,password);
    }

    @Override
    public void start(Stage stage) throws Exception {
        loginController.setCurrentStage(stage);
        currentStage = stage;
        FXMLLoader fxmlloader =new FXMLLoader();
        fxmlloader.setController(loginController);
        fxmlloader.setLocation(getClass().getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlloader.load());
        stage.setTitle("Welcome to Credit Kris");
        stage.setScene(scene);
        stage.show();
    }

    int loginAttempt(String username, String password) throws Exception{
        LoginObject loginObject = createLoginObject(username,password);
        Customer returnedCustomer = loginObject.loginAttempt();
        if (returnedCustomer != null ){
            launchCustomerApplication(returnedCustomer);
            return 0;
        }
        else {
            return -1;
        }
    }

    void launchCustomerApplication(Customer customer)throws Exception{
        new CustomerApplication(customer).start(currentStage);
    }
}
