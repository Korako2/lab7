package client.commands;

import lombok.Getter;
import sharedClasses.commands.commandsUtils.ArgObject;
import sharedClasses.commands.commandsUtils.Manager;
import sharedClasses.data.MusicBand;

public class ArgObjectForClient extends ArgObject {
    @Getter
    private final ClientCommandsManager clientCommandsManager;

    public ArgObjectForClient(ClientCommandsManager clientCommandsManager, String[] args, MusicBand musicBand) {
        super(args, musicBand);
        this.clientCommandsManager = clientCommandsManager;
    }

    @Override
    public ClientCommandsManager getManager() {
        return clientCommandsManager;
    }
}
