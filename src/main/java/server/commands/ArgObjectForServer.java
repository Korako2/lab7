package server.commands;

import lombok.Getter;
import server.collectionUtil.CollectionManager;
import sharedClasses.commands.commandsUtils.ArgObject;
import sharedClasses.data.MusicBand;

public class ArgObjectForServer extends ArgObject {
    @Getter
    private final CollectionManager collectionManager;

    public ArgObjectForServer(CollectionManager collectionManager, String[] args, MusicBand musicBand) {
        super(args, musicBand);
        this.collectionManager = collectionManager;
    }

    @Override
    public CollectionManager getManager() {
        return collectionManager;
    }
}
