package sharedClasses.commands;

import server.collectionUtil.CollectionManager;
import sharedClasses.commands.commandsUtils.ArgObject;

/**
 * Класс для удаления элементов из коллекции по ID.
 */
public class RemoveById extends Command {
    public RemoveById() {
        super(false, 1, "REMOVE_BY_ID", "to remove an element from the collection by its id", true);
    }

    @Override
    public String execute(ArgObject argObject) {
        String result = "Element successfully removed";
        try {
            long id = Long.parseLong(argObject.getArgs()[1]);
            boolean resultOfRemoval = ((CollectionManager)argObject.getManager()).removeById(id);
            if (!resultOfRemoval) result = "This id wasn't found";
        } catch (NumberFormatException e) {
            result = "Wrong format of id";
        }
        return result;
    }

}
