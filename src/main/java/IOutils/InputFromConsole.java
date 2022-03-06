package IOutils;

import commands.Command;
import commands.CommandsManager;
import commands.NameOfCommands;

import java.util.*;

public class InputFromConsole {
    private final Map<NameOfCommands, Command> commandMap;

    private final Scanner input;

    public InputFromConsole(CommandsManager commandsManager, Scanner input) {
        commandMap = commandsManager.getCommandMap();
        this.input=input;
    }

    public void input() {
        String[] s = input.nextLine().split(" ");
        commandMap.get(NameOfCommands.valueOf(s[0].toUpperCase(Locale.ROOT))).execute();
    }







}
