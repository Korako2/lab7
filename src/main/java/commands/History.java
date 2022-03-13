package commands;

import commands.commandsUtils.ArgObject;
import commands.commandsUtils.NameOfCommands;

import java.util.LinkedList;

public class History extends Command {
    public History() {
        super(false, 0, NameOfCommands.HISTORY, "output the last 11 commands (without their arguments)");
    }

    public String execute(ArgObject argObject) {
        LinkedList<NameOfCommands> history = argObject.getCommandsManager().getHistory();
        StringBuilder result = new StringBuilder();
        for (NameOfCommands name : history) {
            result.append(name.getName()).append("\n");
        }
        return result.toString();
    }
}
