package commands;

import collection.MusicBand;
import commands.commandsUtils.ArgObject;

public class Show extends Command{

    public Show() {
        super(false, 0, NameOfCommands.SHOW, "print to standard output all the elements of the collection in the string representation");
    }

    public String execute(ArgObject argObject) {
        StringBuilder result = new StringBuilder();
        for (MusicBand i: argObject.getCollectionStorage().getMusicBands()) {
            result.append(i.toString()).append("\n");
        }
        return result.substring(0, result.toString().length() - 1);
    }
}
