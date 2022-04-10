package commands;

import commands.commandsUtils.ArgObject;

/**
 * Класс для добавления нового элемента в коллекцию.
 */
public class Add extends Command {
    public Add() {
        super(true, 0, "ADD", "output help for available commands");
    }

    public String execute(ArgObject argObject) {
        argObject.getMusicBand().setId(argObject.getCollectionManager().generateId());
        argObject.getCollectionManager().add(argObject.getMusicBand());
        return "Music band was added.";
    }
}
