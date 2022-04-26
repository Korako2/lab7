package server.commands;

import lombok.Getter;
import server.collectionUtil.CollectionManager;
import sharedClasses.commands.commandsUtils.ArgObject;
import sharedClasses.data.MusicBand;

/**
 * A class for storing fragments of server commands.
 */
public class ArgObjectForServer extends ArgObject {
    @Getter
    private final CollectionManager collectionManager;

    public ArgObjectForServer(CollectionManager collectionManager, String[] args, MusicBand musicBand) {
        super(args, musicBand);
        this.collectionManager = collectionManager;
    }
}
