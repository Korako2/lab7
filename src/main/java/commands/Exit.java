package commands;

import commands.commandsUtils.ArgObject;
import commands.commandsUtils.NameOfCommands;

public class Exit extends Command {
    public Exit() {
        super(false, 0, NameOfCommands.EXIT, "terminate the program (without saving to a file)");
    }

    public String execute(ArgObject argObject) {
        return "Program is finishing...:(";
    }
}
