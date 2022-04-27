package commands;

import commands.commandsUtils.ArgObjectForServer;
import commands.commandsUtils.CommandResult;
import messageUtils.ResponseCode;

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
