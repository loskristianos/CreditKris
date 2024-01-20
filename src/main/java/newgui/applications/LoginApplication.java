package newgui.applications;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import newdao.CustomerService;
import newgui.controllers.LoginController;
import newobjects.Customer;

public class LoginApplication extends Application {

    LoginController loginController;
    Stage currentStage;

    public LoginApplication(){
        loginController = createLoginController();
        loginController.setLoginApplication(this);
    }

    private LoginController createLoginController(){
        return new LoginController();
    }

    public void displayScreen(){
        launch();
    }

    public void start(Stage stage) throws Exception{
        currentStage = stage;
        FXMLLoader fxmlloader =new FXMLLoader();
        fxmlloader.setController(loginController);
        fxmlloader.setLocation(getClass().getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlloader.load());
        stage.setTitle("Welcome to Credit Kris");
        stage.setScene(scene);
        stage.show();
    }

    public int loginAttempt(String username, String password){
        CustomerService customerService = CustomerService.getCustomerService();
        Customer returnedCustomer = customerService.getLogin(username, password);
        if (returnedCustomer != null){
            launchCustomerApplication(returnedCustomer);
            return 0;
        } else return -1;
    }

    public void launchCustomerApplication(Customer customer){
        new CustomerApplication(customer).start(currentStage);
    }

    public void launchNewCustomerApplication(){
        // new NewCustomerApplication.start(currentStage);
    }
}
