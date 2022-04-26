package client.commands;

import client.commands.commandsUtils.ArgObjectForClient;
import sharedClasses.commands.Command;

import java.util.LinkedList;

/**
 * The output class of the last 11 commands.
 */
public class History extends Command<ArgObjectForClient> {
    public History() {
        super(false, 0, "HISTORY", "output the last 11 commands (without their arguments)", false);
    }

    public String execute(ArgObjectForClient argObject) {
        LinkedList<String> history = (argObject.getClientCommandManager()).getHistory();
        StringBuilder result = new StringBuilder();
        for (String name : history) {
            result.append(name).append("\n");
        }
        return result.substring(0, result.toString().length() - 1);
    }
}
