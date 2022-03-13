package commands;

import collection.MusicBand;
import commands.commandsUtils.ArgObject;
import commands.commandsUtils.NameOfCommands;

public class FilterStartsWithDescription extends Command {
    public FilterStartsWithDescription() {

        super(false, 1, NameOfCommands.FILTER_STARTS_WITH_DESCRIPTION, "output elements whose description field value starts with the specified substring");
    }

    @Override
    public String execute(ArgObject argObject) {
        StringBuilder result = new StringBuilder();
        for (MusicBand i : argObject.getCollectionStorage().getCollection()) {
            if (i.getDescription().indexOf(argObject.getArgs()[1]) == 0) {
                result.append(i).append("\n");
            }
        }
        if (result.isEmpty()) return "No such elements";
        return result.substring(0, result.toString().length() - 1);
    }
}
