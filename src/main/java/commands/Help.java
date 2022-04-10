package commands;

import commands.commandsUtils.ArgObject;

import java.util.Map;

/**
 * Класс для вывода справки по доступным командам.
 */
public class Help extends Command {
    public Help() {
        super(false, 0, "HELP", "display help on available commands");
    }

    @Override
    public String execute(ArgObject argObject) {
        return argObject.getCommandsManager().getCommandsDescription();
    }
}
