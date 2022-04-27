package messageUtils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import commands.Command;
import data.MusicBand;

import java.io.Serializable;

/**
 * The class of the request to the server.
 */
@RequiredArgsConstructor
public class Request implements Serializable {
    private static final long serialVersionUID = 1L;
    @Getter
    private final Command command;
    @Getter
    private final String[] argsOfCommand;
    @Getter
    private final MusicBand musicBand;

    public boolean isEmpty() {
        return command == null;
    }

    public String toString() {
        return "Request: " + command.getName() + ", " + musicBand.toString();
    }

    public String getNameOfCommand() {
        return command.getName();
    }
}
