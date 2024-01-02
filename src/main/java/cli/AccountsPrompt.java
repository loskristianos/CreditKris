package cli;

import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.ListResult;
import de.codeshelf.consoleui.prompt.PromtResultItemIF;
import de.codeshelf.consoleui.prompt.builder.ListItemBuilder;
import de.codeshelf.consoleui.prompt.builder.ListPromptBuilder;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;

import static org.fusesource.jansi.Ansi.ansi;

public class AccountsPrompt {
    HashMap<String,String> customerDetails;
    List<String> accountList;
        public AccountsPrompt(List<HashMap<String,String>> inputList,HashMap<String,String> customer) {
            customerDetails = customer;
            accountList = new ArrayList<>();
            for (HashMap<String,String> map : inputList) {
                StringJoiner x = new StringJoiner("\t|\t","[\t","\t]");
                x.add(map.get("accountNumber"));
                x.add(map.get("accountType"));
                x.add(map.get("currentBalance"));
                x.add(map.get("overdraftLimit"));
                accountList.add(x.toString());
            }
        }

        public String displayPrompt(){
            String name = customerDetails.get("firstName")+" "+customerDetails.get("lastName");
            String customerID = customerDetails.get("customerID");
            System.out.println(ansi().eraseScreen().render("Current accounts for "+name+" ("+customerID+")\n"+
                    "Please select an account to view"));


            try {
                ConsolePrompt prompt = new ConsolePrompt();
                PromptBuilder promptBuilder = prompt.getPromptBuilder();
                ListPromptBuilder listPromptBuilder = promptBuilder.createListPrompt();
                ListItemBuilder listItemBuilder = new ListItemBuilder(listPromptBuilder);
                listPromptBuilder.name("accountSelection").message("Select an account to view Transactions");
                for (String account : accountList) {
                    listItemBuilder.name("").text(account).add();
                }
                listPromptBuilder.addPrompt();

                HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
                ListResult listResult = (ListResult) result.get("accountSelection");
                return listResult.getSelectedId();
            } catch (IOException e){
                System.out.println(e.getMessage());
                return null;
            }

        }
}
