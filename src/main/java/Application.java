import IOutils.InputFromConsole;
import collection.collectionUtil.CollectionStorage;
import commands.commandsUtils.CommandsManager;

import java.util.Scanner;

public class Application {
    public static void main(String[] arg){
        Scanner scanner = new Scanner(System.in);
        CommandsManager commandsManager = new CommandsManager();
        CollectionStorage collectionStorage = new CollectionStorage();
        collectionStorage.fillCollection("");
        InputFromConsole inputFromConsole = new InputFromConsole(commandsManager, scanner, collectionStorage);
        inputFromConsole.input();
    }
}
