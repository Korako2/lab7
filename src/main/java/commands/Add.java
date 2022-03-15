package commands;

import commands.commandsUtils.ArgObject;
import commands.commandsUtils.NameOfCommands;

/**
 * Класс для добавления нового элемента в коллекцию.
 */
public class Add extends Command {
    public Add() {
        super(true, 0, NameOfCommands.ADD, "output help for available commands");
    }

    public String execute(ArgObject argObject) {
        argObject.getMusicBand().setId(argObject.getCollectionStorage().generateID());
        argObject.getCollectionStorage().add(argObject.getMusicBand());
        return "Music band was added.";
    }
}
