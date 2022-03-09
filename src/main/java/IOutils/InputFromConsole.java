package IOutils;

import collection.MusicBand;
import collection.collectionUtil.CollectionStorage;
import commands.Command;
import commands.commandsUtils.ArgObject;
import commands.commandsUtils.CommandsManager;
import commands.NameOfCommands;

import java.util.*;

public class InputFromConsole {
    private final Map<NameOfCommands, Command> commandMap;
    private final CollectionStorage collectionStorage;
    private final ObjectReader objectReader;
    private final Scanner input;

    public InputFromConsole(CommandsManager commandsManager, Scanner input, CollectionStorage collectionStorage) {
        commandMap = commandsManager.getCommandMap();
        this.input = input;
        objectReader = new ObjectReader(input);
        this.collectionStorage = collectionStorage;
    }

    public void input() {
        String[] s = input.nextLine().split(" ");
        Command command = commandMap.get(NameOfCommands.valueOf(s[0].toUpperCase(Locale.ROOT)));
        if (!checkArgsCount(s, command)) return;
        ArgObject argObject = new ArgObject(collectionStorage, s, readObjectIfNecessary(command));
        command.execute(argObject);
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
