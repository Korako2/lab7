package commands;

import commands.commandsUtils.ArgObjectForServer;
import messageUtils.ResponseCode;
import commands.commandsUtils.CommandResult;
import data.MusicGenre;

import java.util.Set;

/**
 * A class for displaying the unique values of the genre field of all elements in the collection.
 */
public class PrintUniqueGenre extends Command<ArgObjectForServer> {
    public PrintUniqueGenre() {
        super(false, 0, "PRINT_UNIQUE_GENRE", "print the unique values of the genre field of all items in the collection", true);
    }

    @Override
    public CommandResult execute(ArgObjectForServer argObject) {
        Set<MusicGenre> filterResult = argObject.getCollectionManager().PrintUniqueGenre();
        StringBuilder result = new StringBuilder();
        for (MusicGenre musicGenre : filterResult) result.append(musicGenre.getMusic()).append("\n");
        if (result.length() == 0) return new CommandResult("No genre...\n", ResponseCode.OK);
        return new CommandResult(result.substring(0, result.toString().length() - 1), ResponseCode.OK);
    }
}
