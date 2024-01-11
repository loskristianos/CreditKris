package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class LoginApplication extends Application {

    LoginController loginController;

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
        stage.show();
    }
}
