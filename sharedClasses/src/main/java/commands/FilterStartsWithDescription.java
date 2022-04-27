package commands;

import commands.commandsUtils.ArgObjectForServer;
import commands.commandsUtils.CommandResult;
import messageUtils.ResponseCode;
import data.MusicBand;

import java.util.Set;

/**
 * A class for displaying collection items whose description field value starts with a specified substring.
 */
public class FilterStartsWithDescription extends Command<ArgObjectForServer> {
    public FilterStartsWithDescription() {

        super(false, 1, "FILTER_STARTS_WITH_DESCRIPTION", "output elements whose description field value starts with the specified substring", true);
    }
    @Override
    public CommandResult execute(ArgObjectForServer argObject) {
        Set<MusicBand> filterResult = argObject.getCollectionManager().getMusicBandsOfDescription(argObject.getArgs()[1]);
        StringBuilder result = new StringBuilder();
        for (MusicBand musicBand : filterResult) result.append(musicBand).append("\n");
        if (result.length() == 0) return new CommandResult("No such elements\n", ResponseCode.OK);
        return new CommandResult(result.substring(0, result.toString().length() - 1), ResponseCode.OK);
    }
}
