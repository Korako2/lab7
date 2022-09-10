package messageUtils;

import lombok.Getter;
import commands.Command;
import data.MusicBand;
import security.Account;

import java.io.Serializable;

/**
 * The class of the request to the server.
 */
public class Request implements Serializable {
    private static final long serialVersionUID = 1L;
    @Getter
    private final Command command;
    @Getter
    private final String[] argsOfCommand;
    @Getter
    private final MusicBand musicBand;
    @Getter
    private final Account account;

    public Request(Command command, String[] argsOfCommand, MusicBand musicBand, Account account){
        this.command = command;
        this.argsOfCommand = argsOfCommand;
        this.musicBand = musicBand;
        this.account = account;
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
