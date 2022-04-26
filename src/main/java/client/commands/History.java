package client.commands;

import server.commands.ArgObjectForServer;
import sharedClasses.commands.Command;
import sharedClasses.commands.commandsUtils.ArgObject;

import java.util.LinkedList;

/**
 * Класс вывода последних 11 команд.
 */
public class History extends Command<ArgObjectForClient> {
    public History() {
        super(false, 0, "HISTORY", "output the last 11 commands (without their arguments)", false);
    }

    public String execute(ArgObjectForClient argObject) {
        LinkedList<String> history = (argObject.getManager()).getHistory();
        StringBuilder result = new StringBuilder();
        for (String name : history) {
            result.append(name).append("\n");
        }
        return result.substring(0, result.toString().length() - 1);
    }
}
