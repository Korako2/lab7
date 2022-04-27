package commands.commandsUtils;

import lombok.Getter;
import commands.Command;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * A class for storing command objects and managing the history of commands.
 */
public class ClientCommandsManager {
    private final Map<String, Command> commandMap = new HashMap<>();
    @Getter
    private final LinkedList<String> history = new LinkedList();
    private final byte MAX_LENGTH_OF_HISTORY = 11;

    /**
     * A method for adding a command to the history.
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
