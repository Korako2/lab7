package commands;

import commands.commandsUtils.ArgObject;

/**
 * Класс для обновления значения элемента коллекции, ID которого равен заданному.
 */
public class UpdateId extends Command {
    public UpdateId() {
        super(true, 1, "UPDATE", "update the value for the collection element whose id is equal to the given one");
    }

    @Override
    public String execute(ArgObject argObject) {
        String result = "Element successfully updated";
        try {
            long id = Long.parseLong(argObject.getArgs()[1]);
            boolean resultOfRemoval = argObject.getCollectionManager().removeById(id);
            if (!resultOfRemoval) result = "This id wasn't found";
            argObject.getMusicBand().setId(id);
            argObject.getCollectionManager().add(argObject.getMusicBand());
        } catch (NumberFormatException e) {
            result = "Wrong format of id";
        }
        return result;
    }
}
