package IOutils;

import collection.MusicBand;
import collection.collectionUtil.CollectionStorage;
import commands.Command;
import commands.commandsUtils.ArgObject;
import commands.commandsUtils.CommandsManager;
import commands.commandsUtils.NameOfCommands;

import java.util.*;

/**
 * Класс для распознавания ввода пользователя (из консоли или файла).
 */
public class UserInputManager {
    private final CommandsManager commandsManager;
    private final CollectionStorage collectionStorage;
    private final ObjectReader objectReader;
    private final Scanner input;
    private final boolean showMessages;

    public UserInputManager(CommandsManager commandsManager, Scanner input, CollectionStorage collectionStorage, boolean showMessages) {
        this.commandsManager = commandsManager;
        this.input = input;
        objectReader = new ObjectReader(input, showMessages);
        this.collectionStorage = collectionStorage;
        this.showMessages = showMessages;
    }

    /**
     * Метод для считывания пользовательского ввода.
     *
     * @return true, если выполнение программы может быть продолжено; false, если следует прекратить выполнение программы.
     */
    public boolean input() {
        printInviteMessage();
        if (!input.hasNext()) return false;
        String[] s = input.nextLine().split(" ");
        try {
            Command command = commandsManager.getCommandMap().get(NameOfCommands.valueOf(s[0].toUpperCase(Locale.ROOT)));
            commandsManager.addToHistory(command.getName());
            if (!checkArgsCount(s, command)) return true;
            ArgObject argObject = new ArgObject(collectionStorage, s, readObjectIfNecessary(command), commandsManager);
            if (command.getName() == NameOfCommands.EXIT) {
                if (!askQuestion()) return true;
                System.out.println(command.execute(argObject));
                return false;
            }
            System.out.println(command.execute(argObject));

        } catch (NumberFormatException e) {
            throw e;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("There's no such command");
        }
        return true;
    }

    /**
     * Метод для потверждения действия пользователя.
     *
     * @return true, если пользователь потверждает действие; false, если пользователь отклоняет действие.
     */
    private boolean askQuestion() {
        while (true) {
            System.out.println("yes/no?");
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
        System.out.println("Wrong amount of arguments. Please, try again! (You can use command \"help\" for more information.)");
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
