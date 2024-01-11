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

    private LoginController createLoginController(){
        return new LoginController();
    }
    @Override
    public void start(Stage stage) throws Exception {
        loginController.setCurrentStage(stage);
        FXMLLoader fxmlloader =new FXMLLoader();
        fxmlloader.setController(loginController);
        fxmlloader.setLocation(getClass().getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlloader.load());
        stage.setTitle("Welcome to Credit Kris");
        stage.setScene(scene);
        currentStage = stage;
        stage.show();
    }

    int loginAttempt(String username, String password) throws Exception{
        LoginObject loginObject = new LoginObject(username, password);
        Customer returnedCustomer = loginObject.loginAttempt();
        if (returnedCustomer != null ){
            new CustomerApplication(returnedCustomer).start(currentStage);
            return 0;
        }
        else {
            return -1;
        }

    }
}
