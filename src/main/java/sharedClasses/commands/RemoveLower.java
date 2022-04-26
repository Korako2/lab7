package sharedClasses.commands;

import server.collectionUtil.CollectionManager;
import server.commands.ArgObjectForServer;
import sharedClasses.commands.commandsUtils.ArgObject;

import java.util.Set;

/**
 * Класс для удаления из коллекции всех элементов, значения поля albumsCount которых меньше заданного.
 */
public class RemoveLower extends Command<ArgObjectForServer> {
    public RemoveLower() {
        super(true, 0, "REMOVE_LOWER", "remove from the collection all elements smaller than the specified one", true);
    }

    @Override
    public String execute(ArgObjectForServer argObject) {
        String result = "All lower objects were deleted";
        Set<Long> id = (argObject.getManager()).getIdByLower(argObject.getMusicBand());
        for (Long i : id) {
            (argObject.getManager()).removeById(i);
        }
        return result;
    }
}
