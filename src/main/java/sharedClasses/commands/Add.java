package sharedClasses.commands;

import server.collectionUtil.CollectionManager;
import sharedClasses.commands.commandsUtils.ArgObject;

/**
 * Класс для добавления нового элемента в коллекцию.
 */
public class Add extends Command {
    public Add() {
        super(true, 0, "ADD", "output help for available commands", true);
    }

    public String execute(ArgObject argObject) {
        argObject.getMusicBand().setId(((CollectionManager)argObject.getManager()).generateId());
        ((CollectionManager)argObject.getManager()).add(argObject.getMusicBand());
        return "Music band was added.";
    }
}
