package gui;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.application.Application;
import view.LoginView;

public class LoginScreen extends Application {

    public void displayScreen(){
        launch();
    }

    @Override
    public void start(Stage stage)  {

        // text field username
        TextField usernameInput = new TextField();
        usernameInput.setPromptText("Username");

        // password field password
        PasswordField passwordInput = new PasswordField();
        passwordInput.setPromptText("Password");

        // layout pane text fields (horizontal)
        HBox inputsBox = new HBox(300);
        inputsBox.getChildren().add(usernameInput);
        inputsBox.getChildren().add(passwordInput);
        inputsBox.setSpacing(10);
        inputsBox.setAlignment(Pos.CENTER);

        // login button
        Button loginButton = new Button("Login");
        // create new user button
        Button newUserButton = new Button("New User Signup");

        // layout pane (buttons) (horizontal)
        HBox buttonBox = new HBox(300);
        buttonBox.getChildren().add(loginButton);
        buttonBox.getChildren().add(newUserButton);
        buttonBox.setSpacing(10);
        buttonBox.setAlignment(Pos.CENTER);

        // layout pane vertical (input fields above buttons)
        VBox layoutBox = new VBox();
        layoutBox.getChildren().add(inputsBox);
        layoutBox.getChildren().add(buttonBox);
        layoutBox.setSpacing(20);
        layoutBox.setAlignment(Pos.CENTER);

        // add layout box to scene
        Scene scene = new Scene(layoutBox,800,300);

        // add scene to Stage
        stage.setScene(scene);
        stage.setTitle("Login Screen");
        stage.show();

        // login button action
        loginButton.setOnAction(action -> {
                String username = usernameInput.getText();
                String password = passwordInput.getText();
                stage.close();
                new LoginView().loginAttempt(username,password);
        });
    }

}
