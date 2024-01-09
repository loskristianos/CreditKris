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


public class CreateNewTransactionScreen {
    Account account;
    Customer customer;
    String transactionType;
    Account targetAccount;

    public CreateNewTransactionScreen(Account account, Customer customer, String transactionType){
        this.account = account;
        this.customer = customer;
        this.transactionType = transactionType;
    }

    public CreateNewTransactionScreen(Account account, Customer customer, String transactionType,Account targetAccount){
        this.account = account;
        this.customer = customer;
        this.transactionType = transactionType;
        this.targetAccount = targetAccount;
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
                    String amount = transactionAmount.getText();
                    switch (transactionType) {
                        case "Deposit", "Withdrawal":
                            new CreateNewTransactionView(account, customer, transactionType).createTransaction(amount);break;
                        case "Transfer":
                            new CreateNewTransactionView(account, customer, transactionType, targetAccount).createTransaction(amount);break;
                    }
                    stage.close();
                });
    }
}
