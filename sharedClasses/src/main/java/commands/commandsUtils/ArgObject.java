package commands.commandsUtils;

import data.MusicBand;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * A abstract class for storing command arguments.
 */
@RequiredArgsConstructor
public abstract class ArgObject {
    @Getter
    private final String[] args;
    @Getter
    private final MusicBand musicBand;

}
