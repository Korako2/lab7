package sharedClasses.commands;

import server.commands.ArgObjectForServer;
import sharedClasses.data.MusicBand;

import java.util.Set;

/**
 * A class for displaying collection items whose description field value starts with a specified substring.
 */
public class FilterStartsWithDescription extends Command<ArgObjectForServer> {
    public FilterStartsWithDescription() {

        super(false, 1, "FILTER_STARTS_WITH_DESCRIPTION", "output elements whose description field value starts with the specified substring", true);
    }

    @Override
    public String execute(ArgObjectForServer argObject) {
        Set<MusicBand> filterResult = argObject.getCollectionManager().getMusicBandsOfDescription(argObject.getArgs()[1]);
        StringBuilder result = new StringBuilder();
        for (MusicBand musicBand : filterResult) result.append(musicBand).append("\n");
        if (result.length() == 0) return "No such elements\n";
        return result.substring(0, result.toString().length() - 1);
    }
}
