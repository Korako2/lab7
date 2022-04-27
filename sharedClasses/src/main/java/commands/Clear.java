package commands;

import commands.commandsUtils.ArgObjectForServer;
import commands.commandsUtils.CommandResult;
import messageUtils.ResponseCode;

/**
 * A class for cleaning the collection.
 */
public class Clear extends Command<ArgObjectForServer> {

    public Clear() {
        super(false, 0, "CLEAR", "to clear the collection", true);
    }

    public CommandResult execute(ArgObjectForServer argObject) {
        argObject.getCollectionManager().clear();
        return new CommandResult("Collection is empty now!", ResponseCode.OK);
    }
}
