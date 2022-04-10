package commands;

import commands.commandsUtils.ArgObject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Абстрактный класс команд.
 */
@RequiredArgsConstructor
public abstract class Command {
    @Getter
    private final boolean needObject;
    @Getter
    private final int countOfArgs;
    @Getter
    private final String name;
    @Getter
    private final String description;

    /**
     * Метод для исполнения команды.
     *
     * @param argObject объект для хранения аргументов команды {@link ArgObject}.
     * @return результат выполнения команды.
     */
    public abstract String execute(ArgObject argObject);

}
