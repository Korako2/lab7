package IOutils.consoleUtils;

import IOutils.ObjectReader;
import collection.MusicBand;
import collection.collectionUtil.CollectionStorage;
import commands.Command;
import commands.commandsUtils.ArgObject;
import commands.commandsUtils.CommandsManager;
import commands.NameOfCommands;

import java.util.*;

public class ConsoleManager {
    private final CommandsManager commandsManager;
    private final CollectionStorage collectionStorage;
    private final ObjectReader objectReader;
    private final Scanner input;

    public ConsoleManager(CommandsManager commandsManager, Scanner input, CollectionStorage collectionStorage) {
        this.commandsManager = commandsManager;
        this.input = input;
        objectReader = new ObjectReader(input);
        this.collectionStorage = collectionStorage;
    }

    public boolean input() {
        System.out.println("Enter command(if you don't know commands, enter command \"help\"):");
        String[] s = input.nextLine().split(" ");
        try {
            Command command = commandsManager.getCommandMap().get(NameOfCommands.valueOf(s[0].toUpperCase(Locale.ROOT)));
            commandsManager.addToHistory(command.getName());
            if (!checkArgsCount(s, command)) return true;
            ArgObject argObject = new ArgObject(collectionStorage, s, readObjectIfNecessary(command), commandsManager);
            if (command.getName() == NameOfCommands.EXIT && askQuestion()) {
                System.out.println(command.execute(argObject));
                return false;
            }
            System.out.println(command.execute(argObject));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("There's no such command");
        }
        return true;
    }

    private boolean askQuestion() {
        while (true) {
            System.out.println("yes/no?");
            if (input.nextLine().equals("yes")) return true;
            else {
                if (input.nextLine().equals("no")) return false;
            }
        }
    }

    private boolean checkArgsCount(String[] s, Command command) {
        if (command.getCountOfArgs() == s.length - 1) return true;
        System.out.println("Wrong amount of arguments. Please, try again! (You can use command \"help\" for more information.)");
        return false;
    }

    private MusicBand readObjectIfNecessary(Command command) {
        if (!command.isNeedObject()) return null;
        return objectReader.readObject();
    }
}
