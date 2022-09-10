package commands;

import commands.commandsUtils.ArgObjectForServer;
import commands.commandsUtils.CommandResult;
import messageUtils.ResponseCode;
import data.MusicBand;
import data.StorageInterface;

import java.sql.SQLException;

/**
 * A class for adding a new item to the collection.
 */
public class Add extends Command<ArgObjectForServer> {
    public Add() {
        super(true, 0, "ADD", "output help for available commands", true);
    }

    public CommandResult execute(ArgObjectForServer argObject) {
        StorageInterface<MusicBand> collectionManager = argObject.getCollectionManager();
        try {
            collectionManager.add(argObject.getMusicBand(), argObject.getUserName());
        } catch (SQLException e) {
            return new CommandResult("Problem with database access.", ResponseCode.ERROR);
        }
        return new CommandResult("Music band was added.", ResponseCode.OK);
    }
}
