package commands;

import commands.commandsUtils.ArgObjectForClient;
import commands.commandsUtils.CommandResult;
import messageUtils.ResponseCode;

/**
 * Class for displaying help on available commands.
 */
public class Help extends Command<ArgObjectForClient> {
    public Help() {
        super(false, 0, "HELP", "display help on available commands", false);
    }

    @Override
    public CommandResult execute(ArgObjectForClient argObject) {
        return new CommandResult(argObject.getClientCommandManager().getCommandsDescription(), ResponseCode.OK);
    }
}
