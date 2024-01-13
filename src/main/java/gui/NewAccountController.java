package gui;

import account.Account;
import customer.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class NewAccountController {

    private Account account;
    private List<Customer> signatoryList;
    private NewAccountApplication newAccountApplication;

    @FXML private Label newAccountTitle;
    @FXML private Label overdraftLabel;
    @FXML private CheckBox termsCheckBox;
    @FXML private Button loginButton;
    @FXML private Button newCustomerButton;
    @FXML private ListView<Customer> signatoryListView;
    @FXML private Button openAccountButton;
    @FXML private VBox signatoryVBox;
    @FXML private RadioButton noSignatoryRadioButton;
    @FXML private RadioButton yesSignatoryRadioButton;
    @FXML private Label signatoryLoginLabel;
    @FXML private HBox signatoryButtonsHBox;

    public NewAccountController(){
        signatoryList = new ArrayList<>();
    }

    @FXML private void initialize(){
        // hide the signatory section and disable the openAccount button until the relevant boxes have been ticked
        signatoryVBox.setVisible(false); openAccountButton.setDisable(true);
        signatoryLoginLabel.setVisible(false);
        signatoryButtonsHBox.setVisible(false);
        signatoryListView.setVisible(false);


        String overdraftReplace = overdraftLabel.getText().replace("overdraftLimit","£"+account.getOverdraftLimit());
        String accountReplace = overdraftReplace.replace("accountType", account.getAccountType()).toLowerCase();
        overdraftLabel.setText(accountReplace);

        String titleReplace = newAccountTitle.getText().replace("accountType", account.getAccountType());
        newAccountTitle.setText(titleReplace);
    }
    public void setAccount(Account account){
        this.account = account;
    }

    public void setNewAccountApplication(NewAccountApplication newAccountApplication) {
        this.newAccountApplication = newAccountApplication;
    }

    @FXML private void openAccountButtonAction(){
        newAccountApplication.setSignatoryList(signatoryList);
        newAccountApplication.openAccount();
    }

    @FXML private void newCustomerButtonAction ()throws Exception{
        newAccountApplication.newSignatorySignup();
    }

    @FXML private void loginButtonAction() throws Exception{
        newAccountApplication.existingSignatoryLogin();
    }

    @FXML private void termsCheckBoxAction(){
        signatoryVBox.setVisible(true);
    }

    @FXML private void noSignatoryRadioButtonAction(){
        openAccountButton.setDisable(false);
    }

    @FXML private void yesSignatoryRadioButtonAction(){
        signatoryLoginLabel.setVisible(true);
        signatoryButtonsHBox.setVisible(true);
        signatoryListView.setVisible(true);
    }

    public void addCustomerToSignatories(Customer customer) {
        signatoryListView.getItems().add(customer);
        signatoryList.add(customer);
        openAccountButton.setDisable(false);
    }
}
