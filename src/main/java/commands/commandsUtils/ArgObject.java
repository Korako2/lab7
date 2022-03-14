package commands.commandsUtils;

import collection.MusicBand;
import collection.collectionUtil.CollectionStorage;

/**
 * Класс для хранения аргументов комманд.
 */
public class ArgObject {
    private final CollectionStorage collectionStorage;
    private final String[] args;
    private final MusicBand musicBand;
    private final CommandsManager commandsManager;

    public ArgObject(CollectionStorage collectionStorage, String[] args, MusicBand musicBand, CommandsManager commandsManager) {
        this.collectionStorage = collectionStorage;
        this.args = args;
        this.musicBand = musicBand;
        this.commandsManager = commandsManager;
    }

    public CollectionStorage getCollectionStorage() {
        return collectionStorage;
    }

    public MusicBand getMusicBand() {
        return musicBand;
    }

    public String[] getArgs() {
        return args;
    }

    public CommandsManager getCommandsManager() {
        return commandsManager;
    }

}
