package gui;

import account.Account;
import account.ClientAccount;
import account.CommunityAccount;
import account.SmallBusinessAccount;
import customer.Customer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class CustomerApplication extends Application {
    Customer customer;
    CustomerController customerController;
    Stage currentStage;
    Scene currentScene;
    List<Account> accountList;

    public CustomerApplication(Customer customer){
        this.customer = customer;
        accountList = customer.getAccounts();
        customerController = createCustomerController();
        customerController.setCustomer(customer);
        customerController.setAccountList(accountList);
        customerController.setCustomerApplication(this);
    }
    public CustomerController createCustomerController(){
        return new CustomerController();
    }

    @Override
    public void start(Stage stage) throws Exception {
        customerController.setCurrentStage(stage);
        currentStage = stage;
        FXMLLoader fxmlloader =new FXMLLoader();
        fxmlloader.setController(customerController);
        fxmlloader.setLocation(getClass().getResource("customer-view.fxml"));
        Scene scene = new Scene(fxmlloader.load());
        customerController.setCurrentScene(scene);
        currentScene = scene;
        stage.setTitle("Customer Details");
        stage.setScene(scene);
    }

    Account createNewAccount(String accountType){
        Account newAccount = switch (accountType) {
            case "Client": yield new ClientAccount(customer);
            case "Community": yield new CommunityAccount(customer);
            case "Business": yield new SmallBusinessAccount(customer);
            default: yield null;
        };
        assert newAccount != null;
        newAccount.writeData();
        return newAccount.getThisAccount();
    }

    void selectAccount(Account selectedAccount) throws Exception{
        AccountApplication accountApplication = new AccountApplication(selectedAccount,customer);
        accountApplication.setAccountList(accountList);
        accountApplication.setPreviousScene(currentScene);
        accountApplication.start(currentStage);
    }
}
