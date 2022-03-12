package commands;

import commands.commandsUtils.ArgObject;

public class ExecuteScript extends Command{
    public ExecuteScript() {
        super(false, 1, NameOfCommands.EXECUTE_SCRIPT, "read and execute the script from the specified file. The script contains commands in the same form in which they are entered by the user in\n" +
                "interactive mode.");
    }

    @Override
    public String execute(ArgObject argObject) {

        return null;
    }
}
