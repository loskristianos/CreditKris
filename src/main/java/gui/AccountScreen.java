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
import transaction.Transaction;

import java.util.List;

public class AccountScreen extends Application {
    Account account;
    Customer customer;
    List<DataObject> transactionList;

    public AccountScreen(Account inputAccount, Customer inputCustomer, List<DataObject> inputList){
        this.account = inputAccount;
        this.customer = inputCustomer;
        this.transactionList = inputList;
    }

    @Override
    public void start(Stage stage){
        // get account details out of the Account object and customer Name
        String accountNumber = account.getAccountNumber();
        String accountType = account.getAccountType();
        String currentBalance = account.getCurrentBalance();
        String overdraftLimit = account.getOverdraftLimit();
        String customerName = customer.getFullName();

        // initialise a GridPane for the details half
        GridPane accountDetailsPane = new GridPane();

        // GridPane padding
        accountDetailsPane.setHgap(10);
        accountDetailsPane.setVgap(10);

        // add account details to the grid
        accountDetailsPane.add(new Label("Account Number: "),0,0,1,1);
        accountDetailsPane.add(new Label(accountNumber),1,0,1,1);
        accountDetailsPane.add(new Label("Account Type: "),2,0,1,1);
        accountDetailsPane.add(new Label(accountType + " Account"),3,0,1,1);
        accountDetailsPane.add(new Label("Current Balance:"),0,1,1,1);  // to move further down the pane
        accountDetailsPane.add(new Label(currentBalance),1,1,1,1);         // to move further down the pane
        accountDetailsPane.add(new Label("Overdraft Limit"),2,1,1,1);   // to move down
        accountDetailsPane.add(new Label(overdraftLimit),3,1,1,1);         // to move down

        // initialise a TableView
        TableView<Transaction> transactionTableView = new TableView<>();

        // set the column headers
        TableColumn<Transaction, String> transactionTime = new TableColumn<>("Transaction Date");
        TableColumn<Transaction,String> transactionID = new TableColumn<>("Transaction Reference");
        TableColumn<Transaction, String> transactionType = new TableColumn<>("Transaction Type");
        TableColumn<Transaction, String> transactionAmount = new TableColumn<>("Transaction Amount");
        TableColumn<Transaction, String> previousBalance = new TableColumn<>("Previous Balance");
        TableColumn<Transaction,String> newBalance = new TableColumn<>("New Balance");

        // set values for the columns
        transactionTime.setCellValueFactory(new PropertyValueFactory<>("transactionTime"));
        transactionID.setCellValueFactory(new PropertyValueFactory<>("transactionID"));
        transactionType.setCellValueFactory(new PropertyValueFactory<>("transactionType"));
        transactionAmount.setCellValueFactory(new PropertyValueFactory<>("transactionAmount"));
        previousBalance.setCellValueFactory(new PropertyValueFactory<>("previousBalance"));
        newBalance.setCellValueFactory(new PropertyValueFactory<>("newBalance"));

        // add columns to the table
        transactionTableView.getColumns().add(transactionTime);
        transactionTableView.getColumns().add(transactionID);
        transactionTableView.getColumns().add(transactionType);
        transactionTableView.getColumns().add(transactionAmount);
        transactionTableView.getColumns().add(previousBalance);
        transactionTableView.getColumns().add(newBalance);

        // set the cell values for each transaction in the list
        for (DataObject object : transactionList) {
            Transaction transaction = (Transaction) object;
            transactionTableView.getItems().add(transaction);
        }

        // initialise SplitPane
        SplitPane splitPane = new SplitPane(accountDetailsPane,transactionTableView);
        splitPane.setOrientation(Orientation.VERTICAL);

        Scene scene = new Scene(splitPane);
        stage.setScene(scene);
        stage.setTitle("Viewing details for " + accountType + " account number" + accountNumber);
        stage.show();
    }
}
