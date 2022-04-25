package client.IOutils;

import client.IOutils.readers.ObjectReader;
import client.commands.ClientCommandsManager;
import sharedClasses.data.MusicBand;
import sharedClasses.commands.Command;
import sharedClasses.Request;

import java.io.PrintStream;
import java.util.*;

/**
 * Класс для распознавания ввода пользователя (из консоли или файла).
 */
public class UserInputManager {
    private final ClientCommandsManager clientcommandsManager;
    private final ObjectReader objectReader;
    private final Scanner input;
    private final boolean showMessages;
    private final PrintStream out;
    public UserInputManager(ClientCommandsManager clientCommandsManager, Scanner input,  boolean showMessages, PrintStream printStream) {
        this.clientcommandsManager = clientCommandsManager;
        this.input = input;
        objectReader = new ObjectReader(input, showMessages);
        this.showMessages = showMessages;
        this.out = printStream;
    }

    /**
     * Метод для считывания пользовательского ввода.
     *
     * @return true, если выполнение программы может быть продолжено; false, если следует прекратить выполнение программы.
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
     * Метод для потверждения действия пользователя.
     *
     * @return true, если пользователь потверждает действие; false, если пользователь отклоняет действие.
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
     * Метод для проверки количества параметров комманд.
     *
     * @param s       массив пользовательского ввода.
     * @param command команда, введенная пользователем.
     * @return true, если количество аргументов верное, иначе false.
     */
    private boolean checkArgsCount(String[] s, Command command) {
        if (command.getCountOfArgs() == s.length - 1) return true;
        return false;
    }

    /**
     * Метод для чтения элемента коллекции, если это необходимо для выполнения команды.
     *
     * @param command текущая исполняемая команда.
     * @return прочитанный объект {@link MusicBand} или null, если объект не требуется.
     */
    private MusicBand readObjectIfNecessary(Command command) {
        if (!command.isNeedObject()) return null;
        return objectReader.readObject();
    }

    /**
     * Метод для вывода приглашения к вводу для пользователя (в случае чтения данных с консоли).
     */
    private void printInviteMessage() {
        if (showMessages) System.out.println("Enter command (if you don't know commands, enter command \"help\"):");
    }
}
