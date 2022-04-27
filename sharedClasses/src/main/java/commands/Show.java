package commands;

import commands.commandsUtils.ArgObjectForServer;
import commands.commandsUtils.CommandResult;
import messageUtils.ResponseCode;

/**
 * A class for displaying all elements of a collection in a string representation.
 */
public class Show extends Command<ArgObjectForServer> {

    public Show() {
        super(false, 0, "SHOW", "print to standard output all the elements of the collection in the string representation", true);
    }

    public CommandResult execute(ArgObjectForServer argObject) {
        return new CommandResult(argObject.getCollectionManager().show(), ResponseCode.OK);
    }
}
