package commands;

import collection.MusicBand;
import commands.commandsUtils.ArgObject;

import java.util.ArrayList;

public class RemoveLower extends Command{
    public RemoveLower() {
        super(true, 0, NameOfCommands.REMOVE_LOWER, "remove from the collection all elements smaller than the specified one");
    }

    @Override
    public String execute(ArgObject argObject) {
        String result="All lower objects were deleted";
        ArrayList<Long>  ID = new ArrayList<>();
        for (MusicBand i : argObject.getCollectionStorage().getMusicBands()) {
            if (argObject.getMusicBand().getAlbumsCount() > i.getAlbumsCount()) {
                ID.add(i.getId());
            }
        }
        for (Long i : ID) {
            argObject.getCollectionStorage().removeById(i);
        }
        return result;
    }
}
