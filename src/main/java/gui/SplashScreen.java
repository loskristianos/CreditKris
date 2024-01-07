package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import view.LoginView;

public class SplashScreen extends Application {

    public void displayScreen(){
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        // display image (to come)

        // login button
        Button button = new Button("Go!");


        stage.setTitle("Welcome to Credit Kris!");
        Scene scene = new Scene(button);
        stage.setScene(scene);
        stage.show();

        button.setOnAction(actionEvent -> {
            new LoginView().displayView();
            stage.close();
        });
    }
}
