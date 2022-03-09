package commands;

import commands.commandsUtils.ArgObject;

public class Add extends Command {
    public Add() {
        super(true, 0);
    }

    public void execute(ArgObject argObject) {
        System.out.println("Выполнелось add, ТЫ КРУТ!");
    }
}
