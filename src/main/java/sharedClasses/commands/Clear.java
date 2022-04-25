package sharedClasses.commands;

import server.collectionUtil.CollectionManager;
import sharedClasses.commands.commandsUtils.ArgObject;

/**
 * Класс для очистки коллекции.
 */
public class Clear extends Command {

    public Clear() {
        super(false, 0, "CLEAR", "to clear the collection", true);
    }

    public String execute(ArgObject argObject) {
        ((CollectionManager)argObject.getManager()).clear();
        return "Collection is empty now!";
    }
}
