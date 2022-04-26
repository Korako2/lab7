package sharedClasses.messageUtils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sharedClasses.commands.Command;
import sharedClasses.data.MusicBand;

import java.io.Serializable;

@RequiredArgsConstructor
public class Request implements Serializable {
    @Getter
    private final Command command;
    @Getter
    private final String[] argsOfCommand;
    @Getter
    private final MusicBand musicBand;
    private static final long serialVersionID = 1L;

    public boolean isEmpty() {
        return command == null;
    }

    public String toString() {
        return "Request[" + command.getName() + ", " + musicBand.toString() + "]";
    } //todo

    public String getNameOfCommand() {
        return command.getName();
    }
}
