package sharedClasses.commands;

import sharedClasses.commands.commandsUtils.ArgObject;

/**
 * Класс для вывода всех элементов коллекции в строковом представлении.
 */
public class Show extends Command {

    public Show() {
        super(false, 0, "SHOW", "print to standard output all the elements of the collection in the string representation");
    }

    public String execute(ArgObject argObject) {
        return argObject.getCollectionManager().show();
    }
}
