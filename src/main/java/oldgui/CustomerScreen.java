package oldgui;

import account.Account;
import account.ClientAccount;
import account.CommunityAccount;
import account.SmallBusinessAccount;
import customer.Customer;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import view.AccountView;

import java.util.List;

public class CustomerScreen  {
    List<Account> accountList;
    Customer customer;
    public CustomerScreen(List<Account> inputAccounts, Customer inputCustomer){
        accountList = inputAccounts;
        customer = inputCustomer;
    }

    /*  Still to add:
    *       'Edit customer details' button in top pane
    *       highlighting on account list to show any pending transactions
    *
    */

    public void displayScreen(){
        start();
    }

    public void start() {
        // get some String details out of the Customer object
        String customerID = customer.getCustomerID();
        String customerName = customer.getFullName();
        String dob = customer.getDob();
        String customerAddress = customer.getFullAddress();

        //initialise a BorderPane to hold the elements in the top half
        BorderPane borderPane = new BorderPane();

        // initialise a GridPane for the customer details
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


        // add some buttons to an HBox - individual for now, we'll make them conditional later on
        Button newClientAccount = new Button("New Client Account");
        Button newBusinessAccount = new Button("New Business Account");
        Button newCommunityAccount = new Button("New Community Account");
        HBox customerButtons = new HBox(newClientAccount, newBusinessAccount, newCommunityAccount);

        borderPane.setTop(customerPane);
        borderPane.setBottom(customerButtons);

        // initialise a TableView for the bottom half (list of accounts)
        TableView<Account> accountsTable = new TableView<>();

        // set column headers
        TableColumn<Account,String> accountNumber = new TableColumn<>("Account Number");
        TableColumn<Account, String> accountType = new TableColumn<>("Account Type");
        TableColumn<Account, String> accountBalance = new TableColumn<>("Current Balance");

        // set the values that will be pulled from the Account objects
        accountNumber.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
        accountType.setCellValueFactory(new PropertyValueFactory<>("accountType"));
        accountBalance.setCellValueFactory(new PropertyValueFactory<>("currentBalance"));

        // add the columns to the table
        accountsTable.getColumns().add(accountNumber);
        accountsTable.getColumns().add(accountType);
        accountsTable.getColumns().add(accountBalance);

        // for each object in the list, get the relevant cell values we set above
        for (Account account : accountList) {
            accountsTable.getItems().add(account);
        }

        // initialise SplitPane and set orientation to vertical (split with one above the other)
        SplitPane splitPane = new SplitPane(borderPane, accountsTable);
        splitPane.setOrientation(Orientation.VERTICAL);

        // add to scene and add to stage
        Scene scene = new Scene(splitPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Customer Accounts for " + customerName );
        stage.show();

        // when row selected from accounts list launch AccountScreen (showing account details and transactions)
        accountsTable.setOnMouseClicked(mouseEvent -> {
            Account selectedAccount = accountsTable.getSelectionModel().getSelectedItem();
            new AccountView(selectedAccount,customer,accountList).displayView();
            stage.close();
        });

        // create new Accounts from buttons
        newClientAccount.setOnAction(actionEvent -> {
            Account x = new ClientAccount(customer);
            x.writeData();
            Account y = x.getThisAccount();
            accountList.add(y);
            accountsTable.getItems().add(y);
            accountsTable.refresh();
        });

        newBusinessAccount.setOnAction(actionEvent -> {
            Account x = new SmallBusinessAccount(customer);
            x.writeData();
            Account y = x.getThisAccount();
            accountList.add(y);
            accountsTable.getItems().add(y);
            accountsTable.refresh();
        });

        newCommunityAccount.setOnAction(actionEvent -> {
            Account x = new CommunityAccount(customer);
            x.writeData();
            Account y = x.getThisAccount();
            accountList.add(y);
            accountsTable.getItems().add(y);
            accountsTable.refresh();
        });

    }
}
