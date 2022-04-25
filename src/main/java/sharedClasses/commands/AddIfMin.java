package sharedClasses.commands;

import server.collectionUtil.CollectionManager;
import sharedClasses.commands.commandsUtils.ArgObject;

/**
 * Класс для добавления элемента в коллекцию, если его поле albumsCount меньше минимального внутри коллекции.
 */
public class AddIfMin extends Command {
    public AddIfMin() {
        super(true, 0, "ADD_IF_MIN", " add a new element to the collection if its value is less than the smallest element of this collection", true);
    }

    public String execute(ArgObject argObject) {
        String result = "This band wasn't added because it has too much albums";
        if (argObject.getMusicBand().compareTo(((CollectionManager)argObject.getManager()).getMinObject()) < 0) {
            ((CollectionManager)argObject.getManager()).add(argObject.getMusicBand());
            result = "Music band was added.";
        }
        return result;
    }
}
