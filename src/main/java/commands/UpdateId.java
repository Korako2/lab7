package commands;

import commands.commandsUtils.ArgObject;
import commands.commandsUtils.NameOfCommands;

public class UpdateId extends Command {
    public UpdateId() {
        super(true, 1, NameOfCommands.UPDATE, "update the value for the collection element whose id is equal to the given one");
    }

    @Override
    public String execute(ArgObject argObject) {
        String result = "Element successfully updated";
        try {
            long id = Long.parseLong(argObject.getArgs()[1]);
            boolean resultOfRemoval = argObject.getCollectionStorage().getCollection().removeIf(musicBand -> musicBand.getId() == id);
            if (!resultOfRemoval) result = "This id wasn't found";
            argObject.getMusicBand().setId(id);
            argObject.getCollectionStorage().add(argObject.getMusicBand());
        } catch (NumberFormatException e) {
            result = "Wrong format of id";
        }
        return result;
    }
}
