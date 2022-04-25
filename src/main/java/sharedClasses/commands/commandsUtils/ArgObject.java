package sharedClasses.commands.commandsUtils;

import client.commands.ClientCommandsManager;
import sharedClasses.data.MusicBand;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Класс для хранения аргументов комманд.
 */
@RequiredArgsConstructor
public class ArgObject {
    @Getter
    private final Manager manager;
    @Getter
    private final String[] args;
    @Getter
    private final MusicBand musicBand;
}
