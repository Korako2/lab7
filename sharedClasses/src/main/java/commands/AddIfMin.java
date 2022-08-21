package commands;

import commands.commandsUtils.ArgObjectForServer;
import commands.commandsUtils.CommandResult;
import messageUtils.ResponseCode;

import java.sql.SQLException;

/**
 * A class for adding an item to a collection if its albumsCount field is less than the minimum inside the collection.
 */
public class AddIfMin extends Command<ArgObjectForServer> {
    public AddIfMin() {
        super(true, 0, "ADD_IF_MIN", " add a new element to the collection if its value is less than the smallest element of this collection", true);
    }

    public CommandResult execute(ArgObjectForServer argObject) {
        String result = "This band wasn't added because it has too much albums";
        if (argObject.getMusicBand().compareTo(argObject.getCollectionManager().getMinObject()) < 0) {
            try {
                argObject.getCollectionManager().add(argObject.getMusicBand(), argObject.getUserName());
            } catch (SQLException e) {
                return new CommandResult("Problem with database access", ResponseCode.ERROR);
            }
            result = "Music band was added.";
        }
        return new CommandResult(result, ResponseCode.OK);
    }
}
