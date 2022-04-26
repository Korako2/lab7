package sharedClasses.commands;

import server.commandsUtils.ArgObjectForServer;
import sharedClasses.commands.commandsUtils.CommandResult;
import sharedClasses.messageUtils.ResponseCode;

/**
 * A class for terminating a program.
 */
public class Exit extends Command<ArgObjectForServer> {
    public Exit() {
        super(false, 0, "EXIT", "terminate the program (without saving to a file)", true);
    }

    public CommandResult execute(ArgObjectForServer argObject) {
        return new CommandResult("Program is finishing.", ResponseCode.OK);
    }
}
