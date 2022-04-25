package client;

import client.IOutils.UserInputManager;
import client.commands.ClientCommandsManager;
import client.commands.ExecuteScript;
import client.commands.Help;
import client.commands.History;
import server.collectionUtil.CollectionManager;
import sharedClasses.commands.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    private static PrintStream out;
    public static void main(String[] arg) {
        out = new PrintStream(System.out);
        AddressValidation addressValidation = new AddressValidation(arg, out);
        if (!addressValidation.checkAddress()) return;
        ClientCommandsManager clientCommandsManager = new ClientCommandsManager();
        Scanner scanner = new Scanner(System.in);
        UserInputManager inputFromConsole = new UserInputManager(clientCommandsManager, scanner, true, out);
        Client client = new Client(addressValidation.getHost(), addressValidation.getPort(), out, inputFromConsole, clientCommandsManager);
        clientCommandsManager.addCommand(new Help());
        clientCommandsManager.addCommand(new Info());
        clientCommandsManager.addCommand(new Show());
        clientCommandsManager.addCommand(new Add());
        clientCommandsManager.addCommand(new UpdateId());
        clientCommandsManager.addCommand(new RemoveById());
        clientCommandsManager.addCommand(new Clear());
        clientCommandsManager.addCommand(new ExecuteScript(client));
        clientCommandsManager.addCommand(new Exit());
        clientCommandsManager.addCommand(new AddIfMin());
        clientCommandsManager.addCommand(new RemoveLower());
        clientCommandsManager.addCommand(new History());
        clientCommandsManager.addCommand(new FilterStartsWithDescription());
        clientCommandsManager.addCommand(new FilterLessThanNumberOfParticipants());
        clientCommandsManager.addCommand(new PrintUniqueGenre());
        try {
            client.run();
        } catch (IOException e) {
            e.getMessage();
        }

    }

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        ClientCommandsManager ClientCommandsManager = new ClientCommandsManager();
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

        UserInputManager inputFromConsole = new UserInputManager(ClientCommandsManager, scanner, true, out);
        boolean continueFlag;
        /*do {
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
        } while (continueFlag);*/
    }
}

