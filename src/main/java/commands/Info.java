package commands;

import commands.commandsUtils.ArgObject;
import commands.commandsUtils.NameOfCommands;

/**
 * Класс для вывода информации о коллекции.
 */
public class Info extends Command {
    public Info() {
        super(false, 0, NameOfCommands.INFO, "write to the standard output information about the collection of (type, date of initialization, the number of elements, etc.)");
    }

    @Override
    public String execute(ArgObject argObject) {
        return "Тип коллекции: " + argObject.getCollectionStorage().getCollection().getClass().getName() + ", дата создания: " + argObject.getCollectionStorage().getDate() + ", количество объектов: " + argObject.getCollectionStorage().getCollection().size();
    }
}
