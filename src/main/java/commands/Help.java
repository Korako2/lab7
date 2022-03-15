package commands;

import commands.commandsUtils.ArgObject;
import commands.commandsUtils.NameOfCommands;

import java.util.Map;

/**
 * Класс для вывода справки по доступным командам.
 */
public class Help extends Command {
    public Help() {
        super(false, 0, NameOfCommands.HELP, "display help on available commands");
    }

    @Override
    public String execute(ArgObject argObject) {
        StringBuilder help = new StringBuilder();
        for (Map.Entry<NameOfCommands, Command> entry : argObject.getCommandsManager().getCommandMap().entrySet()) {
            help.append(entry.getValue().getName().getName()).append(": ").append(entry.getValue().getDescription()).append("\n");
        }
        return help.substring(0, help.length() - 1);
    }
}
