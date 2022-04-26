package sharedClasses.commands;

import server.commands.ArgObjectForServer;
import sharedClasses.commands.commandsUtils.ArgObject;

/**
 * Класс для завершения программы без сохранения в файл.
 */
public class Exit extends Command<ArgObjectForServer> {
    public Exit() {
        super(false, 0, "EXIT", "terminate the program (without saving to a file)", true);
    }
    public String execute(ArgObjectForServer argObject) {
        return "Program is finishing...:(";
    }
}
