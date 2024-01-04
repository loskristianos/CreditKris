package gui;

import account.Account;
import customer.Customer;
import interfaces.DataObject;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import view.AccountView;

import java.util.HashMap;
import java.util.List;

public class CustomerScreen extends Application {
    List<DataObject> accountList;
    Customer customer;
    public CustomerScreen(List<DataObject> inputAccounts, DataObject inputCustomer){
        this.accountList = inputAccounts;
        this.customer = (Customer) inputCustomer;
    }

    /*  Still to add:
    *       'Edit customer details' button in top pane
    *       clickable account in tableview to launch Transactions view
    *       highlighting on account list to show any pending transactions
    *
    */
    @Override
    public void start(Stage stage) {
        // get some String details out of the Customer object
        String customerID = customer.getCustomerID();
        HashMap<String,String> customerDetails = customer.getDetails();
        String customerName = customerDetails.get("firstName") + " " + customerDetails.get("lastName");
        String dob = customerDetails.get("dob");
        String customerAddress = customerDetails.get("address1") +"/n" + customerDetails.get("address2")+"/n" + customerDetails.get("addressTown") + "/n" + customerDetails.get("addressPostcode");

        // initialise a GridPane for the top half of the screen
        GridPane customerPane = new GridPane();

        // set some padding around the elements
        customerPane.setVgap(10);
        customerPane.setHgap(10);

        // add the customer details we pulled out of the object before
        customerPane.add(new Label("Customer Name:"),0,0,1,1);
        customerPane.add(new Label(customerName),1,0,1,1);
        customerPane.add(new Label("Date of Birth"),0,1,1,1);
        customerPane.add(new Label(dob),1,1,1,1);
        customerPane.add(new Label("Customer Reference"),2,0,1,1);
        customerPane.add(new Label(customerID),3,0,1,1);
        customerPane.add(new Label("Customer Address"),2,1,1,1);
        customerPane.add(new Label(customerAddress),3,1,1,1);

        // initialise a TableView for the bottom half (list of accounts)
        TableView<Account> accountsTable = new TableView<>();

        // set column headers
        TableColumn<Account,String> accountNumber = new TableColumn<>("Account Number");
        TableColumn<Account, String> accountType = new TableColumn<>("Account Type");
        TableColumn<Account, String> accountBalance = new TableColumn<>("Current Balance");

        // set the values that will be pulled from the Account objects
        accountNumber.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
        accountType.setCellValueFactory(new PropertyValueFactory<>("accountType"));
        accountBalance.setCellValueFactory(new PropertyValueFactory<>("accountBalance"));

        // add the columns to the table
        accountsTable.getColumns().add(accountNumber);
        accountsTable.getColumns().add(accountType);
        accountsTable.getColumns().add(accountBalance);

        // for each object in the list, get the relevant cell values we set above
        for (DataObject object : accountList) {
            Account account = (Account) object;     // cast DataObject to Account to use the account-specific getter methods
            accountsTable.getItems().add(account);
        }

        // initialise SplitPane and set orientation to vertical (split with one above the other)
        SplitPane splitPane = new SplitPane(customerPane, accountsTable);
        splitPane.setOrientation(Orientation.VERTICAL);

        // add to scene and add to stage
        Scene scene = new Scene(splitPane);
        stage.setScene(scene);
        stage.setTitle("Customer Accounts");
        stage.show();

        // when row selected from accounts list launch AccountScreen (showing account details and transactions)
        Account selectedAccount = accountsTable.getSelectionModel().getSelectedItem();
        new AccountView(selectedAccount,customer);
    }
}
