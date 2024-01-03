package cli;

import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.InputResult;
import de.codeshelf.consoleui.prompt.PromtResultItemIF;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import interfaces.UI;
import jline.TerminalFactory;

import org.fusesource.jansi.AnsiConsole;

import java.io.IOException;
import java.util.HashMap;

import static org.fusesource.jansi.Ansi.ansi;

public class LoginPrompt implements UI {

    public void displayPrompt() {
        AnsiConsole.systemInstall();
        System.out.println(ansi().eraseScreen().render("Welcome to CreditKris.\nPlease enter your login details below.\n"));
    }

    public HashMap<String,String> getInputDetails() {
        HashMap<String, String> outputMap = new HashMap<>();
        try {
            ConsolePrompt prompt = new ConsolePrompt();
            PromptBuilder promptBuilder = prompt.getPromptBuilder();


            promptBuilder.createInputPrompt()
                    .name("username")
                    .message("Username: ")
                    .addPrompt();

            promptBuilder.createInputPrompt()
                    .name("password")
                    .message("Password: ")
                    .mask('*')
                    .addPrompt();

            HashMap<String, ? extends PromtResultItemIF> inputFields = prompt.prompt(promptBuilder.build());
            InputResult user = (InputResult) inputFields.get("username");
            String username = user.getInput();
            InputResult pass = (InputResult) inputFields.get("password");
            String password = pass.getInput();
            outputMap.put("username",username);outputMap.put("password",password);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                TerminalFactory.get().restore();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return outputMap;
    }

    @Override
    public String optionSelection() {
        return null;
    }
}