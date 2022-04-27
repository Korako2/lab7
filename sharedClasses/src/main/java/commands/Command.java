package commands;

import commands.commandsUtils.ArgObject;
import commands.commandsUtils.CommandResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * An abstract class of commands.
 */
@RequiredArgsConstructor
public abstract class Command<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Getter
    private final boolean needObject;
    @Getter
    private final int countOfArgs;
    @Getter
    private final String name;
    @Getter
    private final String description;
    @Getter
    private final boolean isServer;

    /**
     * The method for executing the command.
     *
     * @param argObject an object for storing command arguments {@link ArgObject}.
     * @return the result of the command execution.
     */
    public abstract CommandResult execute(T argObject);

}
