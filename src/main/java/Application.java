import IOutils.consoleUtils.ConsoleManager;
import collection.collectionUtil.CollectionStorage;
import commands.commandsUtils.CommandsManager;

import javax.sound.midi.Soundbank;
import java.util.Scanner;

public class Application {
    public static void main(String[] arg) {
        Scanner scanner = new Scanner(System.in);
        CommandsManager commandsManager = new CommandsManager();
        CollectionStorage collectionStorage = new CollectionStorage();
        collectionStorage.fillCollection("collection.csv");
        ConsoleManager inputFromConsole = new ConsoleManager(commandsManager, scanner, collectionStorage);
        while (true) {
            try {
                inputFromConsole.input();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e){
                System.out.println("Some exception!");
                System.out.println(e.getMessage());
            }
        }
    }
}
