package commands.commandsUtils;

import lombok.Getter;
import data.MusicBand;
import data.StorageInterface;

/**
 * A class for storing fragments of server commands.
 */
public class ArgObjectForServer extends ArgObject {
    @Getter
    private final StorageInterface<MusicBand>  collectionManager;

    public ArgObjectForServer(StorageInterface<MusicBand> collectionManager, String[] args, MusicBand musicBand) {
        super(args, musicBand);
        this.collectionManager = collectionManager;
    }
}
