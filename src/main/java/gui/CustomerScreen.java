package gui;

import account.Account;
import customer.Customer;
import interfaces.DataObject;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

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
    public void start(Stage stage) throws Exception {

        String customerID = customer.getCustomerID();
        HashMap<String,String> customerDetails = customer.getDetails();
        String customerName = customerDetails.get("firstName") + " " + customerDetails.get("lastName");
        String dob = customerDetails.get("dob");
        String customerAddress = customerDetails.get("address1") +"/n" + customerDetails.get("address2")+"/n" + customerDetails.get("addressTown") + "/n" + customerDetails.get("addressPostcode");

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


        SplitPane splitPane = new SplitPane(customerPane,accountsTable);
        Scene scene = new Scene(splitPane);
        stage.setScene(scene);
        stage.show();
    }
}
