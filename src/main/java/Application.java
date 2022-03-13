import IOutils.consoleUtils.ConsoleManager;
import collection.collectionUtil.CollectionStorage;
import commands.commandsUtils.CommandsManager;

import java.io.File;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {
    public static void main(String[] arg) {
        Scanner scanner = new Scanner(System.in);
        CommandsManager commandsManager = new CommandsManager();
        CollectionStorage collectionStorage = new CollectionStorage();
        Map env = System.getenv();
        String fileName = (String) env.get("FILE_NAME");
        if (fileName == null) {
            System.out.println("Please, save the file name in environment variable FILE_NAME");
            System.exit(-1);
        }
        Pattern pattern = Pattern.compile("/*/dev/*");
        File file = new File(fileName);
        Matcher matcher = pattern.matcher(file.getAbsolutePath());
        if (matcher.find()) System.exit(-1);
        if (!collectionStorage.fillCollection(fileName)) {
            System.out.println("Incorrect data in file.");
            System.exit(-1);
        }
        ConsoleManager inputFromConsole = new ConsoleManager(commandsManager, scanner, collectionStorage, true);
        boolean continueFlag;
        do {
            try {
                continueFlag = inputFromConsole.input();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continueFlag = true;
            } catch (NoSuchElementException e) {
                continueFlag = false;
            } catch (Exception e) {
                System.out.println("Some exception!");
                System.out.println(e.getMessage());
                continueFlag = false;
            }
        } while (continueFlag);
    }
}
