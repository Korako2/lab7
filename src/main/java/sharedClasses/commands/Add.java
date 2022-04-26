package sharedClasses.commands;

import server.collectionUtil.CollectionManager;
import server.commands.ArgObjectForServer;
import sharedClasses.commands.commandsUtils.ArgObject;

/**
 * Класс для добавления нового элемента в коллекцию.
 */
public class Add extends Command<ArgObjectForServer> {
    public Add() {
        super(true, 0, "ADD", "output help for available commands", true);
    }

    public String execute(ArgObjectForServer argObject) {
        CollectionManager collectionManager = argObject.getManager();
        argObject.getMusicBand().setId((collectionManager).generateId());
        collectionManager.add(argObject.getMusicBand());
        return "Music band was added.";
    }
}
