package sharedClasses.commands;

import server.collectionUtil.CollectionManager;
import server.commands.ArgObjectForServer;
import sharedClasses.commands.commandsUtils.ArgObject;

/**
 * Класс для вывода всех элементов коллекции в строковом представлении.
 */
public class Show extends Command<ArgObjectForServer> {

    public Show() {
        super(false, 0, "SHOW", "print to standard output all the elements of the collection in the string representation", true);
    }

    public String execute(ArgObjectForServer argObject) {
        return (argObject.getManager()).show();
    }
}
