package gui;

import account.Account;
import customer.Customer;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import transaction.Transaction;
import view.CreateNewTransactionView;

import java.util.List;

public class AccountScreen {
    Account account;
    Customer customer;
    List<Transaction> transactionList;
    List<Account> accountList;

    public AccountScreen(Account inputAccount, Customer inputCustomer, List<Transaction> inputTransactionList,List<Account> accountList){
        account = inputAccount;
        customer = inputCustomer;
        transactionList = inputTransactionList;
        this.accountList = accountList;
    }

    public void displayScreen(){
        start();
    }

    public void start(){
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

        // Initialise some buttons
        Button newDeposit = new Button ("Deposit");
        accountDetailsPane.add(newDeposit,0,3,1,1);
        Button newWithdrawal = new Button("Withdrawal");
        accountDetailsPane.add(newWithdrawal,1,3,1,1);
        Button newTransfer = new Button("Transfer");
        // only show the Transfer button if the customer has another account to transfer to
        if (accountList.size()>1){
            accountDetailsPane.add(newTransfer,2,3,1,1);
        }
        // initialise ComboBox listing customer's other accounts, but don't add it yet
        // (will be added when transfer button is clicked, in EventHandler further below)
        ComboBox<String> transferAccounts = new ComboBox<>();
        for (Account account : accountList) {
            if (account.getAccountNumber() != accountNumber)
                transferAccounts.getItems().add(account.getAccountNumber());
        }


        // initialise a TableView
        TableView<Transaction> transactionTableView = new TableView<>();

        // set the column headers
        TableColumn<Transaction, String> transactionTime = new TableColumn<>("Transaction Date");
        TableColumn<Transaction, String> transactionType = new TableColumn<>("Transaction Type");
        TableColumn<Transaction, String> transactionAmount = new TableColumn<>("Transaction Amount");
        TableColumn<Transaction, String> previousBalance = new TableColumn<>("Previous Balance");
        TableColumn<Transaction,String> newBalance = new TableColumn<>("New Balance");
        TableColumn<Transaction,String> transactionID = new TableColumn<>("Transaction Reference");

        // set values for the columns
        transactionTime.setCellValueFactory(new PropertyValueFactory<>("transactionTime"));
        transactionID.setCellValueFactory(new PropertyValueFactory<>("transactionID"));
        transactionType.setCellValueFactory(new PropertyValueFactory<>("transactionType"));
        transactionAmount.setCellValueFactory(new PropertyValueFactory<>("transactionAmount"));
        previousBalance.setCellValueFactory(new PropertyValueFactory<>("previousBalance"));
        newBalance.setCellValueFactory(new PropertyValueFactory<>("newBalance"));

        // add columns to the table
        transactionTableView.getColumns().add(transactionTime);
        transactionTableView.getColumns().add(transactionType);
        transactionTableView.getColumns().add(transactionAmount);
        transactionTableView.getColumns().add(previousBalance);
        transactionTableView.getColumns().add(newBalance);
        transactionTableView.getColumns().add(transactionID);

        // set the cell values for each transaction in the list
        for (Transaction transaction : transactionList) {
            transactionTableView.getItems().add(transaction);
        }

        // initialise SplitPane
        SplitPane splitPane = new SplitPane(accountDetailsPane,transactionTableView);
        splitPane.setOrientation(Orientation.VERTICAL);

        Scene scene = new Scene(splitPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Viewing details for " + accountType + " account number" + accountNumber);
        stage.show();

        // action on transaction buttons
        newDeposit.setOnAction(actionEvent -> {
            new CreateNewTransactionView(account,customer,"Deposit").displayView();
        });

        newWithdrawal.setOnAction((actionEvent -> {
            new CreateNewTransactionView(account,customer,"Withdrawal").displayView();
        }));

        newTransfer.setOnAction(actionEvent -> {
            accountDetailsPane.add(new Label("Select an account to transfer to"),3,3,1,1);
            accountDetailsPane.add(transferAccounts, 4, 3, 1, 1);
            transferAccounts.setOnAction((actionEvent1 -> {
                String selectedAccount = transferAccounts.getSelectionModel().getSelectedItem();
                new CreateNewTransactionView(account, customer, "Transfer", selectedAccount).displayView();
            }));
        });
    }
}
