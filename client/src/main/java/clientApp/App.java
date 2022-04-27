package clientApp;

import IOutils.UserInputManager;
import commands.*;
import commands.commandsUtils.ClientCommandsManager;
import connectionUtils.AddressValidation;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class App {
    private static PrintStream out;
    public static void main(String[] arg) {
        out = new PrintStream(System.out);
        AddressValidation addressValidation = new AddressValidation(arg, out);
        if (!addressValidation.checkAddress()) {
            out.println("Wrong host or port");
            System.exit(-1);
        }
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
            out.println(e.getMessage());
        } catch (InterruptedException e) {
            out.println("Error occur during connecting: " + e.getMessage());
        }
    }
}

