package commands;

import collection.MusicBand;
import commands.commandsUtils.ArgObject;
import commands.commandsUtils.NameOfCommands;

import java.util.ArrayList;

/**
 * Класс для удаления из коллекции всех элементов, значения поля albumsCount которых меньше заданного.
 */
public class RemoveLower extends Command {
    public RemoveLower() {
        super(true, 0, NameOfCommands.REMOVE_LOWER, "remove from the collection all elements smaller than the specified one");
    }

    @Override
    public String execute(ArgObject argObject) {
        String result = "All lower objects were deleted";
        ArrayList<Long> ID = new ArrayList<>();
        for (MusicBand i : argObject.getCollectionStorage().getCollection()) {
            if (argObject.getMusicBand().compareTo(i) > 0) {
                ID.add(i.getId());
            }
        }
        for (Long i : ID) {
            argObject.getCollectionStorage().removeById(i);
        }
        return result;
    }
}
