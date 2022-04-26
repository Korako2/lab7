package sharedClasses.commands;

import server.collectionUtil.CollectionManager;
import server.commands.ArgObjectForServer;
import sharedClasses.commands.commandsUtils.ArgObject;

/**
 * Класс для вывода информации о коллекции.
 */
public class Info extends Command<ArgObjectForServer> {
    public Info() {
        super(false, 0, "INFO", "write to the standard output information about the collection of (type, date of initialization, the number of elements, etc.)", true);
    }

    @Override
    public String execute(ArgObjectForServer argObject) {
        return (argObject.getManager()).getInfo();
    }
}
