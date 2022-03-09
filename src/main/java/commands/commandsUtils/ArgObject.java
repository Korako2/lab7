package commands.commandsUtils;

import collection.MusicBand;
import collection.collectionUtil.CollectionStorage;

public class ArgObject {
    private CollectionStorage collectionStorage;
    private String[] args;
    private MusicBand musicBand;

    public ArgObject(CollectionStorage collectionStorage, String[] args, MusicBand musicBand) {
        this.collectionStorage = collectionStorage;
        this.args = args;
        this.musicBand = musicBand;
    }

    public CollectionStorage getCollectionStorage() {
        return collectionStorage;
    }

    public String[] getArgs() {
        return args;
    }
}
