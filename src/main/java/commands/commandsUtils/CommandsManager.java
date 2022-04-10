package commands.commandsUtils;

import commands.*;
import lombok.Getter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Класс для хранания объектов комманд и управлением историей комманд.
 */
public class CommandsManager {
    private final Map<String, Command> commandMap = new HashMap<>();
    @Getter
    private final LinkedList<String> history = new LinkedList();
    private final byte MAX_LENGTH_OF_HISTORY = 11;

    {
        commandMap.put("HELP", new Help());
        commandMap.put("INFO", new Info());
        commandMap.put("SHOW", new Show());
        commandMap.put("ADD", new Add());
        commandMap.put("UPDATE", new UpdateId());
        commandMap.put("REMOVE_BY_ID", new RemoveById());
        commandMap.put("CLEAR", new Clear());
        commandMap.put("SAVE", new Save());
        commandMap.put("EXECUTE_SCRIPT", new ExecuteScript());
        commandMap.put("EXIT", new Exit());
        commandMap.put("ADD_IF_MIN", new AddIfMin());
        commandMap.put("REMOVE_LOWER", new RemoveLower());
        commandMap.put("HISTORY", new History());
        commandMap.put("FILTER_STARTS_WITH_DESCRIPTION", new FilterStartsWithDescription());
        commandMap.put("FILTER_LESS_THAN_NUMBER_OF_PARTICIPANTS", new FilterLessThanNumberOfParticipants());
        commandMap.put("PRINT_UNIQUE_GENRE", new PrintUniqueGenre());
    }

    /**
     * Метод для добавления команды в историю.
     */
    public void addToHistory(String command) {
        if (history.size() == MAX_LENGTH_OF_HISTORY) {
            history.remove();
        }
        history.add(command);
    }

    public Command getCommand(String command) {
        return commandMap.get(command);
    }

    public String getCommandsDescription() {
        StringBuilder help = new StringBuilder();
        for (Map.Entry<String, Command> entry : commandMap.entrySet()) {
            help.append(entry.getValue().getName()).append(": ").append(entry.getValue().getDescription()).append("\n");
        }
        return help.substring(0, help.length() - 1);
    }
}
