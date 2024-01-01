package cli;

import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.PromtResultItemIF;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import jline.TerminalFactory;
import org.fusesource.jansi.AnsiConsole;

import static org.fusesource.jansi.Ansi.ansi;

public class LoginView {

	public void loginPrompt {
	AnsiConsole.systemInstall();
	System.out.println(ansi().eraseScreen().render());

	try {
	    ConsolePrompt prompt = new ConsolePrompt();
	    PromptBuilder promptBuilder = new prompt.getPromptBuilder();
	    
	    promptBuilder.createInputPrompt()
		.username("username")
		.message("Username: ")
		.addPrompt();

	    promptBuilder.createInputPrompt()
		.name("password");
		.message("Password: ")
		.mask('*');
		.addPrompt;
		
	HashMap<String, ? extends PromptResultItemIF> result = prompt.prompt(promptBuilder.build());
	}
}
