package server.commands;

import lombok.Getter;
import sharedClasses.commands.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Класс для хранания объектов комманд и управлением историей комманд.
 */
public class ServerCommandsManager {
    private final Map<String, Command> commandMap = new HashMap<>();

    /*{
        commandMap.put("INFO", new Info());
        commandMap.put("SAVE", new Save());
        commandMap.put("SHOW", new Show());
        commandMap.put("ADD", new Add());
        commandMap.put("UPDATE", new UpdateId());
        commandMap.put("REMOVE_BY_ID", new RemoveById());
        commandMap.put("CLEAR", new Clear());
        commandMap.put("EXIT", new Exit());
        commandMap.put("ADD_IF_MIN", new AddIfMin());
        commandMap.put("REMOVE_LOWER", new RemoveLower());
        commandMap.put("FILTER_STARTS_WITH_DESCRIPTION", new FilterStartsWithDescription());
        commandMap.put("FILTER_LESS_THAN_NUMBER_OF_PARTICIPANTS", new FilterLessThanNumberOfParticipants());
        commandMap.put("PRINT_UNIQUE_GENRE", new PrintUniqueGenre());
    }*/

    public sharedClasses.commands.Command getCommand(String command) {
        return commandMap.get(command);
    }

}
