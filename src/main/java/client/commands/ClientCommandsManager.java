package client.commands;

import lombok.Getter;
import sharedClasses.commands.Command;
import sharedClasses.commands.*;
import sharedClasses.commands.commandsUtils.Manager;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Класс для хранания объектов комманд и управлением историей комманд.
 */
public class ClientCommandsManager implements Manager {
    private final Map<String, Command> commandMap = new HashMap<>();
    @Getter
    private final LinkedList<String> history = new LinkedList();
    private final byte MAX_LENGTH_OF_HISTORY = 11;

    /**
     * Метод для добавления команды в историю.
     */
    public void addToHistory(String command) {
        if (history.size() == MAX_LENGTH_OF_HISTORY) {
            history.remove();
        }
        history.add(command);
    }

    public void addCommand(Command command) {
        commandMap.put(command.getName(), command);
    }
    public sharedClasses.commands.Command getCommand(String command) {
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
