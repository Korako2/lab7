package sharedClasses.commands;

import server.collectionUtil.CollectionManager;
import server.commandsUtils.ArgObjectForServer;
import sharedClasses.commands.commandsUtils.CommandResult;
import sharedClasses.messageUtils.ResponseCode;

/**
 * A class for adding a new item to the collection.
 */
public class Add extends Command<ArgObjectForServer> {
    public Add() {
        super(true, 0, "ADD", "output help for available commands", true);
    }

    public CommandResult execute(ArgObjectForServer argObject) {
        CollectionManager collectionManager = argObject.getCollectionManager();
        argObject.getMusicBand().setId(collectionManager.generateId());
        collectionManager.add(argObject.getMusicBand());
        return new CommandResult("Music band was added.", ResponseCode.OK);
    }
}
