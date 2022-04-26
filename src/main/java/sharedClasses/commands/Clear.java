package sharedClasses.commands;

import server.collectionUtil.CollectionManager;
import server.commands.ArgObjectForServer;
import sharedClasses.commands.commandsUtils.ArgObject;

/**
 * Класс для очистки коллекции.
 */
public class Clear extends Command<ArgObjectForServer> {

    public Clear() {
        super(false, 0, "CLEAR", "to clear the collection", true);
    }

    public String execute(ArgObjectForServer argObject) {
        (argObject.getManager()).clear();
        return "Collection is empty now!";
    }
}
