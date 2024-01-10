package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class StartApplication extends Application {

    LoginController loginController;

    public StartApplication(){
        loginController = new LoginController();
    }

    public void displayScreen(){
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        loginController.setCurrentStage(stage);
        FXMLLoader fxmlloader =new FXMLLoader();
        fxmlloader.setController(loginController);
        fxmlloader.setLocation(getClass().getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlloader.load());
        loginController.setCurrentScene(scene);
        stage.setTitle("Welcome to Credit Kris");
        stage.setScene(scene);
        stage.show();
    }
}
