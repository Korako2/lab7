package sharedClasses.commands.commandsUtils;

import sharedClasses.data.MusicBand;
import server.collectionUtil.CollectionManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Класс для хранения аргументов комманд.
 */
@RequiredArgsConstructor
public class ArgObject {
    @Getter
    private final CollectionManager collectionManager;
    @Getter
    private final String[] args;
    @Getter
    private final MusicBand musicBand;
    @Getter
    private final CommandsManager commandsManager;

}
