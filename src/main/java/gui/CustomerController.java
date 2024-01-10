package gui;

import account.Account;
import account.ClientAccount;
import account.CommunityAccount;
import account.SmallBusinessAccount;
import customer.Customer;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.util.List;

public class CustomerController {

    Customer customer;
    List<Account> accountList;
    Stage currentStage;
    Scene previousScene;

    @FXML private TextField customerNameDisplay;
    @FXML private TextField dobDisplay;
    @FXML private TextField customerIdDisplay;
    @FXML private TextArea customerAddressDisplay;
    @FXML private TableView<Account> accountsTable;
    @FXML private TableColumn<Account,String> accountNumberColumn;
    @FXML private TableColumn<Account,String> accountTypeColumn;
    @FXML private TableColumn<Account,String> currentBalanceColumn;
    @FXML private TableColumn<Account,String> overdraftLimitColumn;


    public CustomerController(){
    }
    public void setCustomer(Customer customer){
        this.customer = customer;
    }
    public void setCurrentStage(Stage stage){
        currentStage = stage;
    }
    public void setPreviousScene(Scene scene){
        previousScene = scene;
    }
    @FXML private void initialize(){
        customerNameDisplay.setText(customer.getFullName());
        dobDisplay.setText(customer.getDob());
        customerIdDisplay.setText(customer.getCustomerID());
        customerAddressDisplay.setText(customer.getFullAddress());
        accountList = customer.getAccounts();

        accountNumberColumn.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
        accountTypeColumn.setCellValueFactory(new PropertyValueFactory<>("accountType"));
        currentBalanceColumn.setCellValueFactory(new PropertyValueFactory<>("currentBalance"));
        overdraftLimitColumn.setCellValueFactory(new PropertyValueFactory<>("overdraftLimit"));

        for (Account account : accountList){
            accountsTable.getItems().add(account);
        }

    }
    @FXML void newClientButtonAction(){
        createNewAccount("Client");
    }

    @FXML void newCommunityButtonAction(){
        createNewAccount("Community");
    }

    @FXML void newBusinessButtonAction(){
        createNewAccount("Business");
    }

    void createNewAccount(String accountType){
        Account newAccount = switch (accountType) {
            case "Client": yield new ClientAccount(customer);
            case "Community": yield new CommunityAccount(customer);
            case "Business": yield new SmallBusinessAccount(customer);
            default: yield null;
        };
        assert newAccount != null;
        newAccount.writeData();
        Account completedAccount = newAccount.getThisAccount();
        accountList.add(completedAccount);
        accountsTable.getItems().add(completedAccount);
        accountsTable.refresh();
    }

}
