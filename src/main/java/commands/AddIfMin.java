package commands;

import commands.commandsUtils.ArgObject;

public class AddIfMin extends Command{
    public AddIfMin() {
        super(true, 0, NameOfCommands.ADD_IF_MIN, " add a new element to the collection if its value is less than the smallest element of this collection");
    }

    public String execute(ArgObject argObject) {
        String result = "This band wasn't added because it has too much albums";
        if (argObject.getMusicBand().getAlbumsCount() < argObject.getCollectionStorage().getMinObject().getAlbumsCount()) {
            argObject.getCollectionStorage().add(argObject.getMusicBand());
            result = "Music band was added.";
        }
        return result;
    }
}
