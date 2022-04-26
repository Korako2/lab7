package client.commands.commandsUtils;

import lombok.Getter;
import sharedClasses.commands.commandsUtils.ArgObject;
import sharedClasses.data.MusicBand;

/**
 * A class for storing arguments of client commands.
 */
public class ArgObjectForClient extends ArgObject {
    @Getter
    private final ClientCommandsManager clientCommandsManager;

    public ArgObjectForClient(ClientCommandsManager clientCommandsManager, String[] args, MusicBand musicBand) {
        super(args, musicBand);
        this.clientCommandsManager = clientCommandsManager;
    }

    public ClientCommandsManager getClientCommandManager() {
        return clientCommandsManager;
    }
}
