package IOutils.consoleUtils;

import IOutils.ObjectReader;
import collection.MusicBand;
import collection.collectionUtil.CollectionStorage;
import commands.Command;
import commands.commandsUtils.ArgObject;
import commands.commandsUtils.CommandsManager;
import commands.commandsUtils.NameOfCommands;

import java.util.*;

public class ConsoleManager {
    private final CommandsManager commandsManager;
    private final CollectionStorage collectionStorage;
    private final ObjectReader objectReader;
    private final Scanner input;
    private final boolean showMessages;

    public ConsoleManager(CommandsManager commandsManager, Scanner input, CollectionStorage collectionStorage, boolean showMessages) {
        this.commandsManager = commandsManager;
        this.input = input;
        objectReader = new ObjectReader(input, showMessages);
        this.collectionStorage = collectionStorage;
        this.showMessages = showMessages;
    }

    public boolean input() {
        printInviteMessage();
        if (!input.hasNext()) return false;
        String[] s = input.nextLine().split(" ");
        try {
            Command command = commandsManager.getCommandMap().get(NameOfCommands.valueOf(s[0].toUpperCase(Locale.ROOT)));
            commandsManager.addToHistory(command.getName());
            if (!checkArgsCount(s, command)) return true;
            ArgObject argObject = new ArgObject(collectionStorage, s, readObjectIfNecessary(command), commandsManager);
            if (command.getName() == NameOfCommands.EXIT) {
                if (!askQuestion()) return true;
                System.out.println(command.execute(argObject));
                return false;
            }
            System.out.println(command.execute(argObject));

        } catch (NumberFormatException e) {
            throw e;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("There's no such command");
        }
        return true;
    }

    private boolean askQuestion() {
        while (true) {
            System.out.println("yes/no?");
            if (!input.hasNext()) return true;
            String answer = input.nextLine();
            if (answer.equals("yes")) return true;
            if (answer.equals("no")) return false;
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

    private void printInviteMessage() {
        if (showMessages) System.out.println("Enter command (if you don't know commands, enter command \"help\"):");
    }
}
