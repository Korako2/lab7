package commands;

import commands.commandsUtils.ArgObject;
import commands.commandsUtils.NameOfCommands;

public abstract class Command {
    private final boolean needObject;
    private final int countOfArgs;
    private final NameOfCommands name;
    private final String description;

    public Command(boolean needObject, int countOfArgs, NameOfCommands name, String description) {
        this.needObject = needObject;
        this.countOfArgs = countOfArgs;
        this.name = name;
        this.description = description;
    }

    public abstract String execute(ArgObject argObject);

    public NameOfCommands getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isNeedObject() {
        return needObject;
    }

    public int getCountOfArgs() {
        return countOfArgs;
    }
}
