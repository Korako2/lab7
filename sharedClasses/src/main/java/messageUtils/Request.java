package messageUtils;

import lombok.Getter;
import commands.Command;
import data.MusicBand;

import java.io.Serializable;

/**
 * The class of the request to the server.
 */
public class Request implements Serializable {
    private static final long serialVersionUID = 1L;
    @Getter
    private final Command command;
    @Getter
    private String[] argsOfCommand;
    @Getter
    private MusicBand musicBand;

    public Request(Command command, String[] argsOfCommand, MusicBand musicBand){
        this.command = command;
        this.argsOfCommand = argsOfCommand;
        this.musicBand = musicBand;
    }
    public Request(Command command) {
        this.command = command;
    }
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
