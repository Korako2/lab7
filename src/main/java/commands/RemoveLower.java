package commands;

import commands.commandsUtils.ArgObject;

import java.util.Set;

/**
 * Класс для удаления из коллекции всех элементов, значения поля albumsCount которых меньше заданного.
 */
public class RemoveLower extends Command {
    public RemoveLower() {
        super(true, 0, "REMOVE_LOWER", "remove from the collection all elements smaller than the specified one");
    }

    @Override
    public String execute(ArgObject argObject) {
        String result = "All lower objects were deleted";
        Set<Long> id = argObject.getCollectionManager().getIdByLower(argObject.getMusicBand());
        for (Long i : id) {
            argObject.getCollectionManager().removeById(i);
        }
        return result;
    }
}
