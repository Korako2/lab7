package commands;

import commands.commandsUtils.ArgObject;

/**
 * Класс для завершения программы без сохранения в файл.
 */
public class Exit extends Command {
    public Exit() {
        super(false, 0, "EXIT", "terminate the program (without saving to a file)");
    }

    public String execute(ArgObject argObject) {
        return "Program is finishing...:(";
    }
}
