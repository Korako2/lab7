package IOutils;

import IOutils.readers.ObjectReader;
import commands.commandsUtils.ClientCommandsManager;
import data.MusicBand;
import commands.Command;
import messageUtils.Request;

import java.io.PrintStream;
import java.util.*;

/**
 * A class for recognizing user input (from the console or file).
 */
public class UserInputManager {
    private final ClientCommandsManager clientcommandsManager;
    private final ObjectReader objectReader;
    private final Scanner input;
    private final boolean showMessages;
    private final PrintStream out;

    public UserInputManager(ClientCommandsManager clientCommandsManager, Scanner input, boolean showMessages, PrintStream printStream) {
        this.clientcommandsManager = clientCommandsManager;
        this.input = input;
        objectReader = new ObjectReader(input, showMessages);
        this.showMessages = showMessages;
        this.out = printStream;
    }

    /**
     * A method for reading user input.
     *
     * @return true if the program execution can be continued; false if the program execution should be stopped.
     */
    public Request input() {
        printInviteMessage();
        if (!input.hasNext()) return null;
        String[] s = input.nextLine().split(" ");
        Command command = clientcommandsManager.getCommand(s[0].toUpperCase(Locale.ROOT));
        try {
            if (command == null) {
                throw new IllegalArgumentException("There's no such command");
            }
            clientcommandsManager.addToHistory(command.getName());
            if (!checkArgsCount(s, command))
                throw new IllegalArgumentException("Wrong amount of arguments. Please, try again! (You can use command \"help\" for more information.)");
        } catch (NumberFormatException e) {
            throw e;
        }
        return new Request(command, s, readObjectIfNecessary(command));
    }

    /**
     * A method for confirming the user's action.
     *
     * @return true if the user confirms the action; false if the user rejects the action.
     */
    private boolean askQuestion() {
        while (true) {
            out.println("yes/no?");
            if (!input.hasNext()) return true;
            String answer = input.nextLine();
            if (answer.equals("yes")) return true;
            if (answer.equals("no")) return false;
        }
    }

    /**
     * Method for checking the number of command parameters.
     *
     * @param s       array of user input.
     * @param command the command entered by the user.
     * @return true if the number of arguments is correct, otherwise false.
     */
    private boolean checkArgsCount(String[] s, Command command) {
        if (command.getCountOfArgs() == s.length - 1) return true;
        return false;
    }

    /**
     * A method for reading a collection item, if necessary to execute a command.
     *
     * @param command the current executable command.
     * @return read object {@link MusicBand} or null if the object is not required.
     */
    private MusicBand readObjectIfNecessary(Command command) {
        if (!command.isNeedObject()) return null;
        return objectReader.readObject();
    }

    /**
     * A method for displaying an input prompt for the user (in the case of reading data from the console).
     */
    private void printInviteMessage() {
        if (showMessages) System.out.println("Enter command (if you don't know commands, enter command \"help\"):");
    }
}
