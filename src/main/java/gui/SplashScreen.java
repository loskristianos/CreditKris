package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class SplashScreen extends Application {

    public void displayScreen(){
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlloader =new FXMLLoader();
        fxmlloader.setLocation(getClass().getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlloader.load());
        stage.setTitle("Welcome to Credit Kris");
        stage.setScene(scene);
        stage.show();
    }
}
