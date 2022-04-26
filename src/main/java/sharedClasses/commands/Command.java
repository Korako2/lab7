package sharedClasses.commands;

import sharedClasses.commands.commandsUtils.ArgObject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Абстрактный класс команд.
 */
@RequiredArgsConstructor
public abstract class Command<T> implements Serializable {
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
     * Метод для исполнения команды.
     *
     * @param argObject объект для хранения аргументов команды {@link ArgObject}.
     * @return результат выполнения команды.
     */
    public abstract String execute(T argObject);

}
