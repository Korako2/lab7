package sharedClasses.commands;

import sharedClasses.commands.commandsUtils.ArgObject;

/**
 * Класс для вывода информации о коллекции.
 */
public class Info extends Command {
    public Info() {
        super(false, 0, "INFO", "write to the standard output information about the collection of (type, date of initialization, the number of elements, etc.)");
    }

    @Override
    public String execute(ArgObject argObject) {
        return argObject.getCollectionManager().getInfo();
    }
}
