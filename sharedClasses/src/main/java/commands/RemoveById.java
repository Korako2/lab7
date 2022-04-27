package commands;

import commands.commandsUtils.ArgObjectForServer;
import commands.commandsUtils.CommandResult;
import messageUtils.ResponseCode;

/**
 * A class for deleting items from a collection by ID.
 */
public class RemoveById extends Command<ArgObjectForServer> {
    public RemoveById() {
        super(false, 1, "REMOVE_BY_ID", "to remove an element from the collection by its id", true);
    }

    @Override
    public CommandResult execute(ArgObjectForServer argObject) {
        String result = "Element successfully removed";
        try {
            long id = Long.parseLong(argObject.getArgs()[1]);
            boolean resultOfRemoval = (argObject.getCollectionManager()).removeById(id);
            if (!resultOfRemoval) result = "This id wasn't found";
        } catch (NumberFormatException e) {
            return new CommandResult("Wrong format of id", ResponseCode.ERROR);
        }
        return new CommandResult(result, ResponseCode.OK);
    }

}
