import IOutils.InputFromConsole;
import collection.MusicBand;
import collection.collectionUtil.CollectionStorage;
import commands.CommandsManager;

import java.io.IOException;
import java.util.Scanner;

public class Application {
    public static void main(String[] arg){
        Scanner scanner = new Scanner(System.in);
        CommandsManager commandsManager = new CommandsManager();
        InputFromConsole inputFromConsole = new InputFromConsole(commandsManager, scanner);
        inputFromConsole.input();
    }
}
