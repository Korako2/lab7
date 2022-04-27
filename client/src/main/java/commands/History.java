package commands;

import commands.commandsUtils.ArgObjectForClient;
import commands.commandsUtils.CommandResult;
import messageUtils.ResponseCode;

import java.util.LinkedList;

/**
 * The output class of the last 11 commands.
 */
public class History extends Command<ArgObjectForClient> {
    public History() {
        super(false, 0, "HISTORY", "output the last 11 commands (without their arguments)", false);
    }

    public CommandResult execute(ArgObjectForClient argObject) {
        LinkedList<String> history = (argObject.getClientCommandManager()).getHistory();
        StringBuilder result = new StringBuilder();
        for (String name : history) {
            result.append(name).append("\n");
        }
        return new CommandResult(result.substring(0, result.toString().length() - 1), ResponseCode.OK);
    }
}
