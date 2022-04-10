package commands;

import collection.MusicBand;
import commands.commandsUtils.ArgObject;

import java.util.Set;

/**
 * Класс для вывода элементов коллекции, значение поля description которых начинается с заданной подстроки.
 */
public class FilterStartsWithDescription extends Command {
    public FilterStartsWithDescription() {

        super(false, 1, "FILTER_STARTS_WITH_DESCRIPTION", "output elements whose description field value starts with the specified substring");
    }

    @Override
    public String execute(ArgObject argObject) {
        Set<MusicBand> filterResult = argObject.getCollectionManager().getMusicBandsOfDescription(argObject.getArgs()[1]);
        StringBuilder result = new StringBuilder();
        for (MusicBand musicBand : filterResult) result.append(musicBand).append("\n");
        if (result.length() == 0) return "No such elements\n";
        return result.substring(0, result.toString().length() - 1);
    }
}
