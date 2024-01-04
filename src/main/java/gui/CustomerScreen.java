package gui;

import account.Account;
import interfaces.DataObject;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;

public class CustomerScreen extends Application {
    List<DataObject> accountList;
    public CustomerScreen(List<DataObject> inputAccounts){
        this.accountList = inputAccounts;
    }

    /*  split pane vertical
     *   top half - customer details
     *   bottom half - table view list of accounts (clickable to launch relevant transactions list
     *   highlighting on account list to show any pending transactions
     */
    @Override
    public void start(Stage stage) throws Exception {

        // text customerName = firstName + lastName
        // text customerID = customerID
        // text dob = dob
        // text customerAddress = address1 +/n + address2+/n + addressTown + /n + addressPostcode

        GridPane customerPane = new GridPane();

        customerPane.add(new Label("Customer Name:"),0,0,1,1);
        customerPane.add(new Label("Date of Birth"),0,1,1,1);
        customerPane.add(new Label("Customer Reference"),2,0,1,1);

        // tableView accountTable
        TableView accountsTable = new TableView();

        TableColumn<Account,String> accountNumber = new TableColumn<>("Account Number");
        TableColumn<Account, String> accountType = new TableColumn<>("Account Type");
        TableColumn<Account, String> accountBalance = new TableColumn("Current Balance");

        accountNumber.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
        accountType.setCellValueFactory(new PropertyValueFactory<>("accountType"));
        accountBalance.setCellValueFactory(new PropertyValueFactory<>("accountBalance"));

        accountsTable.getColumns().add(accountNumber);
        accountsTable.getColumns().add(accountType);
        accountsTable.getColumns().add(accountBalance);
        for (DataObject object : accountList) {
            Account account = (Account) object;
            accountsTable.getItems().add(account);
        }

        // build the scene
        Scene scene = new Scene(accountsTable);
        stage.setScene(scene);
        stage.show();
    }
}
