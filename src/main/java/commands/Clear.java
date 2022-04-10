package commands;

import commands.commandsUtils.ArgObject;

/**
 * Класс для очистки коллекции.
 */
public class Clear extends Command {

    public Clear() {
        super(false, 0, "CLEAR", "to clear the collection");
    }

    public String execute(ArgObject argObject) {
        argObject.getCollectionManager().clear();
        return "Collection is empty now!";
    }
}
