package commands;

import commands.commandsUtils.ArgObjectForServer;
import commands.commandsUtils.CommandResult;
import messageUtils.ResponseCode;

import java.sql.SQLException;

/**
 * A class for cleaning the collection.
 */
public class Clear extends Command<ArgObjectForServer> {

    public Clear() {
        super(false, 0, "CLEAR", "to clear the collection", true);
    }

    public CommandResult execute(ArgObjectForServer argObject) {
        String result = "All the items you had access to have been deleted";
        ResponseCode responseCode = ResponseCode.OK;
        try {
            argObject.getCollectionManager().clear(argObject.getUserName());
        } catch (SQLException e) {
            result = "Database access problem";
            responseCode = ResponseCode.ERROR;
        }
        return new CommandResult(result, responseCode);
    }
}
