import IOutils.UserInputManager;
import collection.collectionUtil.CollectionManager;
import commands.commandsUtils.CommandsManager;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {
    public static void main(String[] arg) {
        Scanner scanner = new Scanner(System.in);
        CommandsManager commandsManager = new CommandsManager();
        CollectionManager collectionManager = new CollectionManager();
        Map env = System.getenv();
        String fileName = (String) env.get("FILE_NAME");
        if (fileName == null) {
            System.out.println("Please, save the file name in environment variable FILE_NAME");
            System.exit(-1);
        }
       Pattern pattern = Pattern.compile("/*/dev/*");
        File file = new File(fileName);
        Matcher matcher = pattern.matcher(file.getAbsolutePath());
        if (matcher.find()) {
            System.out.println("Incorrect file name or data in file.");
            System.exit(-1);
        }

        try {
            if (!collectionManager.fillCollection(fileName)) {
                System.out.println("Incorrect file name or data in file.");
                System.exit(-1);
            }
        } catch (IOException | ParseException | NumberFormatException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        } catch (Exception e) {
            System.out.println("Some exception!");
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.exit(-1);
        }

        UserInputManager inputFromConsole = new UserInputManager(commandsManager, scanner, collectionManager, true);
        boolean continueFlag;
        do {
            try {
                continueFlag = inputFromConsole.input();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continueFlag = true;
            } catch (NoSuchElementException e) {
                e.printStackTrace();
                continueFlag = false;
            } catch (Exception e) {
                System.out.println("Some exception!");
                e.printStackTrace();
                System.out.println(e.getMessage());
                continueFlag = false;
            }
        } while (continueFlag);
    }
}
