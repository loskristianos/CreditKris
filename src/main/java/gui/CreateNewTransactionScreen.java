package gui;

import account.Account;
import customer.Customer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import view.CreateNewTransactionView;

import java.util.HashMap;

public class CreateNewTransactionScreen {
    Account account;
    Customer customer;
    String transactionType;
    String additionalInfo;

    public CreateNewTransactionScreen(Account account, Customer customer, String transactionType){
        this.account = account;
        this.customer = customer;
        this.transactionType = transactionType;
    }

    public CreateNewTransactionScreen(Account account, Customer customer, String transactionType,String additionalInfo){
        this.account = account;
        this.customer = customer;
        this.transactionType = transactionType;
        this.additionalInfo = additionalInfo;

    }

    public void displayScreen(){
        show();
    }

    public void show(){

        // create Pane for details
        HBox transactionBox = new HBox();

        // Strings from input objects
        String accountNumber = account.getAccountNumber();
        String customerID = customer.getCustomerID();       // need to add customerID to Transaction to show which signatory initiated it
        String currentBalance = account.getCurrentBalance();
        String overdraftLimit = account.getOverdraftLimit();

        Label transactionLabel = new Label("Enter " + transactionType + " amount:");
        TextField transactionAmount = new TextField();

        Button transactionButton = new Button("Confirm " + transactionType);

        transactionBox.getChildren().add(transactionLabel);
        transactionBox.getChildren().add(transactionAmount);
        transactionBox.getChildren().add(transactionButton);

        Stage stage = new Stage();
        Scene scene = new Scene(transactionBox);
        stage.setScene(scene);
        stage.setTitle("New " + transactionType + " Transaction");
        stage.show();

        transactionButton.setOnAction(actionEvent -> {
            HashMap<String,String> transactionDetails = new HashMap<>(){{
                put("accountNumber",accountNumber);put("transactionType",transactionType);put("transactionAmount",transactionAmount.getText());
                put("previousBalance",currentBalance);put("additionalInfo",additionalInfo);
            }};
            new CreateNewTransactionView().createTransaction(transactionDetails);
            stage.close();
        });


        //

    }
}
