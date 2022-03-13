package commands;

import commands.commandsUtils.ArgObject;
import commands.commandsUtils.NameOfCommands;

public class RemoveById extends Command {
    public RemoveById() {
        super(false, 1, NameOfCommands.REMOVE_BY_ID, "to remove an element from the collection by its id");
    }

    @Override
    public String execute(ArgObject argObject) {
        String result = "Element successfully removed";
        try {
            long id = Long.parseLong(argObject.getArgs()[1]);
            boolean resultOfRemoval = argObject.getCollectionStorage().removeById(id);
            if (!resultOfRemoval) result = "This id wasn't found";
        } catch (NumberFormatException e) {
            result = "Wrong format of id";
        }
        return result;
    }

}
