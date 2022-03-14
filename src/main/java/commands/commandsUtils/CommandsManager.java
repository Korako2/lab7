package commands.commandsUtils;

import commands.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Класс для хранания объектов комманд и управлением историей комманд.
 */
public class CommandsManager {
    private final Map<NameOfCommands, Command> commandMap = new HashMap<>();
    private final LinkedList<NameOfCommands> history = new LinkedList();
    private final byte MAX_LENGTH_OF_HISTORY = 11;

    {
        commandMap.put(NameOfCommands.HELP, new Help());
        commandMap.put(NameOfCommands.INFO, new Info());
        commandMap.put(NameOfCommands.SHOW, new Show());
        commandMap.put(NameOfCommands.ADD, new Add());
        commandMap.put(NameOfCommands.UPDATE, new UpdateId());
        commandMap.put(NameOfCommands.REMOVE_BY_ID, new RemoveById());
        commandMap.put(NameOfCommands.CLEAR, new Clear());
        commandMap.put(NameOfCommands.SAVE, new Save());
        commandMap.put(NameOfCommands.EXECUTE_SCRIPT, new ExecuteScript());
        commandMap.put(NameOfCommands.EXIT, new Exit());
        commandMap.put(NameOfCommands.ADD_IF_MIN, new AddIfMin());
        commandMap.put(NameOfCommands.REMOVE_LOWER, new RemoveLower());
        commandMap.put(NameOfCommands.HISTORY, new History());
        commandMap.put(NameOfCommands.FILTER_STARTS_WITH_DESCRIPTION, new FilterStartsWithDescription());
        commandMap.put(NameOfCommands.FILTER_LESS_THAN_NUMBER_OF_PARTICIPANTS, new FilterLessThanNumberOfParticipants());
        commandMap.put(NameOfCommands.PRINT_UNIQUE_GENRE, new PrintUniqueGenre());
    }

    public Map<NameOfCommands, Command> getCommandMap() {
        return commandMap;
    }

    /**
     * Метод для добавления команды в историю.
     *
     * @param command значение enum {@link NameOfCommands}.
     */
    public void addToHistory(NameOfCommands command) {
        if (history.size() == MAX_LENGTH_OF_HISTORY) {
            history.remove();
        }
        history.add(command);
    }

    public LinkedList<NameOfCommands> getHistory() {
        return history;
    }
}
