package commands;

import commands.commandsUtils.ArgObject;

public abstract class Command {
    private boolean needObject;
    private int countOfArgs;
    private NameOfCommands name;
    private String description;
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
