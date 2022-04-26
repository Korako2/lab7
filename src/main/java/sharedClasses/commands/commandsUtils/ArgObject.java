package sharedClasses.commands.commandsUtils;

import client.commands.ClientCommandsManager;
import sharedClasses.data.MusicBand;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Класс для хранения аргументов комманд.
 */
@RequiredArgsConstructor
public abstract class ArgObject {
    @Getter
    private final String[] args;
    @Getter
    private final MusicBand musicBand;

    public abstract Manager getManager();
}
