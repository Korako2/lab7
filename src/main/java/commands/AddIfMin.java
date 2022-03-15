package commands;

import commands.commandsUtils.ArgObject;
import commands.commandsUtils.NameOfCommands;

/**
 * Класс для добавления элемента в коллекцию, если его поле albumsCount меньше минимального внутри коллекции.
 */
public class AddIfMin extends Command {
    public AddIfMin() {
        super(true, 0, NameOfCommands.ADD_IF_MIN, " add a new element to the collection if its value is less than the smallest element of this collection");
    }

    public String execute(ArgObject argObject) {
        String result = "This band wasn't added because it has too much albums";
        if (argObject.getMusicBand().compareTo(argObject.getCollectionStorage().getMinObject()) < 0) {
            argObject.getCollectionStorage().add(argObject.getMusicBand());
            result = "Music band was added.";
        }
        return result;
    }
}
