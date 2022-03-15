package commands;

import commands.commandsUtils.ArgObject;
import commands.commandsUtils.NameOfCommands;

/**
 * Класс для очистки коллекции.
 */
public class Clear extends Command {

    public Clear() {
        super(false, 0, NameOfCommands.CLEAR, "to clear the collection");
    }

    public String execute(ArgObject argObject) {
        argObject.getCollectionStorage().clear();
        return "Collection is empty now!";
    }
}
