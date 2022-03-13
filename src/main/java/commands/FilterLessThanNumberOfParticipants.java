package commands;

import collection.MusicBand;
import commands.commandsUtils.ArgObject;
import commands.commandsUtils.NameOfCommands;

public class FilterLessThanNumberOfParticipants extends Command {
    public FilterLessThanNumberOfParticipants() {
        super(false, 1, NameOfCommands.FILTER_LESS_THAN_NUMBER_OF_PARTICIPANTS, "output elements whose numberOfParticipants field value is less than the specified one");
    }

    @Override
    public String execute(ArgObject argObject) {
        StringBuilder result = new StringBuilder();
        for (MusicBand i : argObject.getCollectionStorage().getCollection()) {
            try {
                if (Long.parseLong(argObject.getArgs()[1]) > i.getNumberOfParticipants()) {
                    result.append(i).append("\n");
                }
            } catch (NumberFormatException e) {
                result = new StringBuilder("Wrong format of number of participants");
            }
        }
        if (result.isEmpty()) return "Collection is empty";
        return result.substring(0, result.toString().length() - 1);
    }
}
