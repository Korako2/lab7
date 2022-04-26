package sharedClasses.commands;

import server.collectionUtil.CollectionManager;
import server.commands.ArgObjectForServer;

/**
 * A class for adding a new item to the collection.
 */
public class Add extends Command<ArgObjectForServer> {
    public Add() {
        super(true, 0, "ADD", "output help for available commands", true);
    }

    public String execute(ArgObjectForServer argObject) {
        CollectionManager collectionManager = argObject.getCollectionManager();
        argObject.getMusicBand().setId(collectionManager.generateId());
        collectionManager.add(argObject.getMusicBand());
        return "Music band was added.";
    }
}
