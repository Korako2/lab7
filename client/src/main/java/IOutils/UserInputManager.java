package IOutils;

import IOutils.readers.ObjectReader;
import commands.commandsUtils.ClientCommandsManager;
import data.MusicBand;
import commands.Command;
import messageUtils.Request;
import security.Account;

import java.io.IOException;
import java.util.*;

import static clientApp.App.out;

/**
 * A class for recognizing user input (from the console or file).
 */
public class UserInputManager {
    private final ClientCommandsManager clientcommandsManager;
    private final ObjectReader objectReader;
    private final Scanner input;
    private final boolean showMessages;
    private final Map<String, String[]> commandWithQuestion = new HashMap<>();
    private final Proceed proceed;

    public UserInputManager(ClientCommandsManager clientCommandsManager, Scanner input, boolean showMessages) {
        this.clientcommandsManager = clientCommandsManager;
        this.input = input;
        objectReader = new ObjectReader(input, showMessages);
        this.showMessages = showMessages;
        proceed = new Proceed(input);
        commandWithQuestion.put("exit", new String[]{"Do you really want to get out?", "yes"});
        commandWithQuestion.put("clear", new String[]{"Do you really want to clean up the collection?", "no"});
    }

    /**
     * A method for reading user input.
     *
     * @return true if the program execution can be continued; false if the program execution should be stopped.
     */
    public Request inputCommand(Account account) throws IOException {
        printInviteMessage();
        if (!input.hasNext()) return null;
        String[] s = input.nextLine().split(" ");
        if (!checkCommand(s[0].toLowerCase(Locale.ROOT))) return new Request(null);
        return createRequest(s, account);
    }
    private Request createRequest(String[] s, Account account) throws IllegalArgumentException {
        Command command = clientcommandsManager.getCommand(s[0].toUpperCase(Locale.ROOT));
        if (command == null) throw new IllegalArgumentException("There's no such command");
        clientcommandsManager.addToHistory(command.getName());
        if (!checkArgsCount(s, command))
            throw new IllegalArgumentException("Wrong amount of arguments. Please, try again! (You can use command \"help\" for more information.)");
        try {
            return new Request(command, s, readObjectIfNecessary(command, account.getUserName())); //todo
        } catch (NoSuchElementException e) {
            return null;
        }
    }
    private boolean checkCommand(String command) {
        if (commandWithQuestion.get(command) != null) {
            return proceed.requestResponse(commandWithQuestion.get(command)[0], commandWithQuestion.get(command)[1]);
        }
        return true;
    }

    /**
     * Method for checking the number of command parameters.
     *
     * @param s       array of user input.
     * @param command the command entered by the user.
     * @return true if the number of arguments is correct, otherwise false.
     */
    private boolean checkArgsCount(String[] s, Command command) {
        return command.getCountOfArgs() == s.length - 1;
    }

    /**
     * A method for reading a collection item, if necessary to execute a command.
     *
     * @param command the current executable command.
     * @return read object {@link MusicBand} or null if the object is not required.
     */
    private MusicBand readObjectIfNecessary(Command command, String userName) {
        if (!command.isNeedObject()) return null;
        return objectReader.readObject(userName);
    }

    /**
     * A method for displaying an input prompt for the user (in the case of reading data from the console).
     */
    private void printInviteMessage() {
        if (showMessages) out.println("Enter command (if you don't know commands, enter command \"help\"):");
    }

}
