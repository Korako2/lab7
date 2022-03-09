package commands;

import commands.commandsUtils.ArgObject;

public abstract class Command {
    private boolean needObject;
    private int countOfArgs;
    public Command(boolean needObject, int countOfArgs) {
        this.needObject = needObject;
        this.countOfArgs = countOfArgs;
    }
    public abstract void execute(ArgObject argObject);

    public boolean isNeedObject() {
        return needObject;
    }

    public int getCountOfArgs() {
        return countOfArgs;
    }
}
