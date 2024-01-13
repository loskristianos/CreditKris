package gui;

import account.Account;
import account.Signatory;
import customer.Customer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;

public class NewAccountApplication extends Application {

    NewAccountController newAccountController;
    CustomerApplication customerApplication;
    Customer customer;
    Account account;
    List<Customer> signatoryList;
    Stage currentStage;
    Scene previousScene;

    public NewAccountApplication(Customer customer, Account account){
        this.customer = customer;
        this.account = account;
        newAccountController = createNewAccountController();
        newAccountController.setNewAccountApplication(this);
        newAccountController.setAccount(account);
    }

    @Override
    public void start(Stage stage) throws Exception {
        currentStage = stage;
        FXMLLoader fxmlloader =new FXMLLoader();
        fxmlloader.setController(newAccountController);
        fxmlloader.setLocation(getClass().getResource("newAccount-view.fxml"));
        Scene scene = new Scene(fxmlloader.load());
        stage.setTitle("Open New Account");
        stage.setScene(scene);
        stage.showAndWait();
    }
    public void setCustomerApplication(CustomerApplication customerApplication){
        this.customerApplication = customerApplication;
    }
    public NewAccountController createNewAccountController(){
        return new NewAccountController();
    }

    public void openAccount(){
        if (signatoryList != null) account.setSignatories(String.valueOf(signatoryList.size()));
        else account.setSignatories("0");
        account.writeData();
        Account returnedAccount = account.getThisAccount();
        String accountNumber = returnedAccount.getAccountNumber();
        if(!account.getSignatories().equals("0")) {
            for (Customer customer : signatoryList){
                HashMap<String,String> map = new HashMap<>(){{put("customerID", customer.getCustomerID()); put("accountNumber",accountNumber);}};
                new Signatory(map).writeData();
            }
        }
        currentStage.close();
        customerApplication.addAccountToList(returnedAccount);
    }

    public void setSignatoryList(List<Customer> signatoryList) {
        this.signatoryList = signatoryList;
    }

    public void newSignatorySignup() throws Exception{
        NewSignatoryApplication newSignatoryApplication = new NewSignatoryApplication();
        newSignatoryApplication.setNewAccountApplication(this);
        newSignatoryApplication.start(new Stage());
    }

    public void existingSignatoryLogin() throws Exception {
        SignatoryLoginApplication newSignatoryLoginApplication = new SignatoryLoginApplication();
        newSignatoryLoginApplication.setNewAccountApplication(this);
        newSignatoryLoginApplication.start(new Stage());
    }

    public void addCustomerToSignatoryListView(Customer customer){
        newAccountController.addCustomerToSignatories(customer);
    }

}