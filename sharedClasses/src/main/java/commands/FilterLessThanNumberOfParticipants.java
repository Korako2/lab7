package commands;

import commands.commandsUtils.ArgObjectForServer;
import commands.commandsUtils.CommandResult;
import messageUtils.ResponseCode;
import data.MusicBand;

import java.util.List;

/**
 * A class for displaying elements whose numberOfParticipants field value is less than the specified one.
 */
public class FilterLessThanNumberOfParticipants extends Command<ArgObjectForServer> {
    public FilterLessThanNumberOfParticipants() {
        super(false, 1, "FILTER_LESS_THAN_NUMBER_OF_PARTICIPANTS", "output elements whose numberOfParticipants field value is less than the specified one", true);
    }

    @Override
    public CommandResult execute(ArgObjectForServer argObject) {
        StringBuilder result = new StringBuilder();
        try {
            Long number = Long.parseLong(argObject.getArgs()[1]);
            List<MusicBand> filterResult = argObject.getCollectionManager().FilterLessThanNumberOfParticipants(number);
            for (MusicBand musicBand : filterResult) result.append(musicBand.toString()).append("\n");
        } catch (NumberFormatException e) {
            return new CommandResult("Wrong format of number of participants\n", ResponseCode.ERROR);
        }
        if (result.length() == 0) return new CommandResult("Collection is empty\n", ResponseCode.OK);
        return new CommandResult(result.substring(0, result.toString().length() - 1), ResponseCode.OK);
    }
}
