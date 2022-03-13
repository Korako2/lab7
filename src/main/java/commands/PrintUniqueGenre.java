package commands;

import collection.MusicBand;
import collection.MusicGenre;
import commands.commandsUtils.ArgObject;
import commands.commandsUtils.NameOfCommands;

import java.util.HashSet;

public class PrintUniqueGenre extends Command {
    public PrintUniqueGenre() {
        super(false, 0, NameOfCommands.PRINT_UNIQUE_GENRE, "print the unique values of the genre field of all items in the collection");
    }

    @Override
    public String execute(ArgObject argObject) {
        HashSet<MusicGenre> musicGenres = new HashSet<>();
        StringBuilder result = new StringBuilder();
        for (MusicBand i : argObject.getCollectionStorage().getCollection()) {
            if (musicGenres.add(i.getGenre())) {
                result.append(i.getGenre().getMusic()).append("\n");
            }
        }
        if (result.isEmpty()) return "No genre...";
        return result.substring(0, result.toString().length() - 1);
    }
}
