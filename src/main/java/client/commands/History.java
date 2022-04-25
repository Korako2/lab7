package client.commands;

import sharedClasses.commands.Command;
import sharedClasses.commands.commandsUtils.ArgObject;

import java.util.LinkedList;

/**
 * Класс вывода последних 11 команд.
 */
public class History extends Command {
    public History() {
        super(false, 0, "HISTORY", "output the last 11 commands (without their arguments)", false);
    }

    public String execute(ArgObject argObject) {
        LinkedList<String> history = ((ClientCommandsManager)argObject.getManager()).getHistory();
        StringBuilder result = new StringBuilder();
        for (String name : history) {
            result.append(name).append("\n");
        }
        return result.toString();
    }
}
