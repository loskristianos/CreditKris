package cli;

import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.ListResult;
import de.codeshelf.consoleui.prompt.PromtResultItemIF;
import de.codeshelf.consoleui.prompt.builder.ListItemBuilder;
import de.codeshelf.consoleui.prompt.builder.ListPromptBuilder;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import interfaces.UI;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;

import static org.fusesource.jansi.Ansi.ansi;

public class TransactionsPrompt implements UI {
    HashMap<String,String> accountDetails;
    List<HashMap<String,String>> transactionList;

    public TransactionsPrompt(List<HashMap<String,String>> inputList, HashMap<String,String> account){
        accountDetails = account;
        transactionList = inputList;
    }


    @Override
    public void displayPrompt() {
        String accountNumber = accountDetails.get("accountNumber");
        String accountType = accountDetails.get("accountType");
        System.out.println(ansi().eraseScreen().render("Transaction Details for "+accountType+" account No. "+accountNumber));
        }


    @Override
    public HashMap<String, String> getInputDetails() {
        return null;
    }

    @Override
    public String optionSelection() {
        try {
            ConsolePrompt prompt = new ConsolePrompt();
            PromptBuilder promptBuilder = prompt.getPromptBuilder();

            ListPromptBuilder listPromptBuilder = promptBuilder.createListPrompt();
            ListItemBuilder listItemBuilder = new ListItemBuilder(listPromptBuilder);
            listPromptBuilder.name("transactionSelection").message("Select a transaction to view details");
            for (HashMap<String,String> map : transactionList) {
                StringJoiner x = new StringJoiner("\t|\t","[\t","\t]");
                    x.add(map.get("transactionType"));
                    x.add(map.get("transactionTime"));
                    x.add(map.get("transactionAmount"));
                    x.add(map.get("newBalance"));
                listItemBuilder.name(map.get("transactionID")).text(x.toString()).add();
            }
            listPromptBuilder.addPrompt();

            HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
            ListResult listResult = (ListResult) result.get("transactionSelection");
            return listResult.getSelectedId();
        } catch (IOException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
