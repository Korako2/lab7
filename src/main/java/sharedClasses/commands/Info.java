package sharedClasses.commands;

import server.collectionUtil.CollectionManager;
import sharedClasses.commands.commandsUtils.ArgObject;

/**
 * Класс для вывода информации о коллекции.
 */
public class Info extends Command {
    public Info() {
        super(false, 0, "INFO", "write to the standard output information about the collection of (type, date of initialization, the number of elements, etc.)", true);
    }

    @Override
    public String execute(ArgObject argObject) {
        return ((CollectionManager)argObject.getManager()).getInfo();
    }
}
