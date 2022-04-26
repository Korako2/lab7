package sharedClasses.commands;

import server.commandsUtils.ArgObjectForServer;
import sharedClasses.commands.commandsUtils.CommandResult;
import sharedClasses.messageUtils.ResponseCode;

/**
 * A class for displaying information about the collection.
 */
public class Info extends Command<ArgObjectForServer> {
    public Info() {
        super(false, 0, "INFO", "write to the standard output information about the collection of (type, date of initialization, the number of elements, etc.)", true);
    }

    @Override
    public CommandResult execute(ArgObjectForServer argObject) {
        return new CommandResult(argObject.getCollectionManager().getInfo(), ResponseCode.OK);
    }
}
