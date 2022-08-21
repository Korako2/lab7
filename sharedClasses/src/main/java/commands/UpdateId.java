package commands;

import commands.commandsUtils.ArgObjectForServer;
import commands.commandsUtils.CommandResult;
import messageUtils.ResponseCode;

import java.sql.SQLException;

/**
 * A class for updating the value of a collection element whose ID is equal to the specified one.
 */
public class UpdateId extends Command<ArgObjectForServer> {
    public UpdateId() {
        super(true, 1, "UPDATE", "update the value for the collection element whose id is equal to the given one", true);
    }

    @Override
    public CommandResult execute(ArgObjectForServer argObject) {
        String result = "Element successfully updated";
        try {
            long id = Long.parseLong(argObject.getArgs()[1]);
            boolean resultOfRemoval = argObject.getCollectionManager().removeById(id, argObject.getUserName());
            if (!resultOfRemoval) result = "This id wasn't found";
            argObject.getMusicBand().setId(id);
            argObject.getCollectionManager().add(argObject.getMusicBand(), argObject.getUserName());
        } catch (NumberFormatException e) {
            return new CommandResult("Wrong format of id", ResponseCode.ERROR);
        } catch (SQLException e) {
            return new CommandResult("Problem with database access", ResponseCode.ERROR);
        }
        return new CommandResult(result, ResponseCode.OK);
    }
}
