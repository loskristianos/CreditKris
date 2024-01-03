package cli;

import de.codeshelf.consoleui.prompt.*;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import interfaces.UI;

import java.io.IOException;
import java.util.HashMap;

import static org.fusesource.jansi.Ansi.ansi;
public class MainMenuPrompt implements UI {
    HashMap<String,String> customerDetails;

    public MainMenuPrompt(HashMap<String,String> customerDetails){
        this.customerDetails = customerDetails;
    }

    public void displayPrompt() {
        String name = customerDetails.get("firstName") + " " + customerDetails.get("lastName");
        String customerID = customerDetails.get("customerID");
        System.out.println(ansi().eraseScreen().render("Welcome to CreditKris.\n\nName: " + name + "Customer Reference: " + customerID
                + "\nPlease choose from the following options."));
    }

    @Override
    public HashMap<String, String> getInputDetails() {
    return null;
    }

    public String optionSelection() {
    try {
            ConsolePrompt prompt = new ConsolePrompt();
            PromptBuilder promptBuilder = prompt.getPromptBuilder();

            promptBuilder.createListPrompt()
                    .name("customerOptions")
                    .message("Customer options:")
                    .newItem("manageAccounts").text("View/Manage Accounts").add()
                    .newItem("editDetails").text("View/Edit Customer Details").add()
                    .newItem("logOut").text("Log Out").add()
                    .addPrompt();

            HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
            ListResult listResult = (ListResult) result.get("customerOptions");
            return listResult.getSelectedId();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }
}
