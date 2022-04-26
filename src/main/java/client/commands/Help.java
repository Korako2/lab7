package client.commands;

import client.commands.commandsUtils.ArgObjectForClient;
import sharedClasses.commands.Command;

/**
 * Class for displaying help on available commands.
 */
public class Help extends Command<ArgObjectForClient> {
    public Help() {
        super(false, 0, "HELP", "display help on available commands", false);
    }

    @Override
    public String execute(ArgObjectForClient argObject) {
        return (argObject.getClientCommandManager()).getCommandsDescription();
    }
}
