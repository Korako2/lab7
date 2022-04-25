package client.commands;

import sharedClasses.commands.Command;
import sharedClasses.commands.commandsUtils.ArgObject;

/**
 * Класс для вывода справки по доступным командам.
 */
public class Help extends Command {
    public Help() {
        super(false, 0, "HELP", "display help on available commands", false);
    }

    @Override
    public String execute(ArgObject argObject) {
        return ((ClientCommandsManager)argObject.getManager()).getCommandsDescription();
    }
}
