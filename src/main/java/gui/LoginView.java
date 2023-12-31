package gui;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.application.Application;

public class LoginView extends Application {

    @Override
    public void start(Stage stage)  {

        // text field username
        TextField usernameInput = new TextField();
        usernameInput.setPromptText("Username");

        // password field password
        PasswordField passwordInput = new PasswordField();
        passwordInput.setPromptText("Password");

        // layout pane text fields (horizontal)
        HBox inputsBox = new HBox();
        inputsBox.getChildren().add(usernameInput);
        inputsBox.getChildren().add(passwordInput);

        // login button
        Button loginButton = new Button("Login");
        // create new user button
        Button newUserButton = new Button("New User Signup");

        // layout pane (buttons) (horizontal)
        HBox buttonBox = new HBox();
        buttonBox.getChildren().add(loginButton);
        buttonBox.getChildren().add(newUserButton);

        // layout pane vertical (input fields above buttons)
        VBox layoutBox = new VBox();
        layoutBox.getChildren().add(inputsBox);
        layoutBox.getChildren().add(buttonBox);

        // add layout box to scene
        Scene scene = new Scene(layoutBox);

        //add scene to Stage
        stage.setScene(scene);
        stage.setTitle("Login Screen");
        stage.show();
    }

}
