package commands;

import commands.commandsUtils.ArgObject;

public class Save extends Command{
    public Save() {
        super(false, 0, NameOfCommands.SAVE, "to save the collection to a file");
    }

    @Override
    public String execute(ArgObject argObject) {

        return null;
    }
}
