package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import view.CreateCustomerView;

import java.util.HashMap;

public class CreateCustomerScreen {

    public void displayScreen(){
        start();
    }
    public void start() {
        // create GridPane for input fields
        GridPane newCustomerPane = new GridPane();

        // set padding
        newCustomerPane.setVgap(10);
        newCustomerPane.setHgap(10);

        // add Labels and Text/Password input fields
        newCustomerPane.add(new Label("Username"),0,0);
        TextField username = new TextField();
        newCustomerPane.add(username,1,0);
        newCustomerPane.add(new Label("Password"),2,0);
        PasswordField password = new PasswordField();
        newCustomerPane.add(password,3,0);
        newCustomerPane.add(new Label("Re-enter Password:"),2,1);
        PasswordField reenterPassword = new PasswordField();
        newCustomerPane.add(reenterPassword,3,1);
        newCustomerPane.add(new Label("First Name:"),0,2);
        TextField firstName = new TextField();
        newCustomerPane.add(firstName,1,2);
        newCustomerPane.add(new Label("Last Name:"),2,2);
        TextField lastName = new TextField();
        newCustomerPane.add(lastName,3,2);
        newCustomerPane.add(new Label("Date of Birth:"),0,3);
        TextField dob = new TextField();
        newCustomerPane.add(dob,1,3);
        newCustomerPane.add(new Label("Address Line 1:"),0,4);
        TextField address1 = new TextField();
        newCustomerPane.add(address1,1,4);
        newCustomerPane.add(new Label("Address Line 2:"),2,4);
        TextField address2 = new TextField();
        newCustomerPane.add(address2,3,4);
        newCustomerPane.add(new Label("Town:"),0,5);
        TextField addressTown = new TextField();
        newCustomerPane.add(addressTown,1,5);
        newCustomerPane.add(new Label("Postcode:"),0,6);
        TextField addressPostcode = new TextField();
        newCustomerPane.add(addressPostcode,1,6);

        // add buttons to submit or exit
        Button submitButton = new Button("Submit");
        Button cancelButton = new Button("Cancel");

        newCustomerPane.add(submitButton,0,7);
        newCustomerPane.add(cancelButton,2,7);

        Stage stage = new Stage();
        Scene scene = new Scene(newCustomerPane);
        stage.setScene(scene);
        stage.setTitle("Create a new customer account");
        stage.show();

        // add actions on submit button
        submitButton.setOnAction(action -> {
            HashMap<String,String> login = new HashMap<>(){{
                put("username",username.getText());put("password",password.getText());
            }};
            HashMap<String,String> customerDetails = new HashMap<>(){{
                put("firstName",firstName.getText());put("lastName",lastName.getText());put("dob",dob.getText());
                put("address1",address1.getText());put("address2",address2.getText());put("addressTown",addressTown.getText());
                put("addressPostcode",addressPostcode.getText());
            }};
            new CreateCustomerView().createCustomer(login,customerDetails);
            stage.close();
        });

        // add action on cancel button
        cancelButton.setOnAction(actionEvent -> {
            stage.close();
            new LoginScreen().start(new Stage());
        });

    }
}
