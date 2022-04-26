package sharedClasses.commands;

import server.collectionUtil.CollectionManager;
import server.commands.ArgObjectForServer;
import sharedClasses.data.MusicBand;
import sharedClasses.commands.commandsUtils.ArgObject;

import java.util.List;

/**
 * Класс для вывода элементов, значения поля numberOfParticipants которых меньше заданного.
 */
public class FilterLessThanNumberOfParticipants extends Command<ArgObjectForServer> {
    public FilterLessThanNumberOfParticipants() {
        super(false, 1, "FILTER_LESS_THAN_NUMBER_OF_PARTICIPANTS", "output elements whose numberOfParticipants field value is less than the specified one", true);
    }

    @Override
    public String execute(ArgObjectForServer argObject) {

        StringBuilder result = new StringBuilder();
        try {
            Long number = Long.parseLong(argObject.getArgs()[1]);
            List<MusicBand> filterResult = (argObject.getManager()).FilterLessThanNumberOfParticipants(number);
            for (MusicBand musicBand : filterResult) result.append(musicBand.toString()).append("\n");
        } catch (NumberFormatException e) {
            result = new StringBuilder("Wrong format of number of participants\n");
        }

        if (result.length() == 0) return "Collection is empty\n";
        return result.substring(0, result.toString().length() - 1);
    }
}
