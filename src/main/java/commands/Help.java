package commands;

import commands.commandsUtils.ArgObject;

import java.util.Iterator;
import java.util.Map;

public class Help extends Command{
    public Help() {
        super(false, 0, NameOfCommands.HELP, "display help on available commands");
    }

    @Override
    public String execute(ArgObject argObject) {
        StringBuilder help = new StringBuilder();
        Iterator<Map.Entry<NameOfCommands, Command>> entries = argObject.getCommandsManager().getCommandMap().entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<NameOfCommands, Command> entry = entries.next();
            help.append(entry.getValue().getName().getName()).append(": ").append(entry.getValue().getDescription()).append("\n");
        }
        return help.substring(0, help.length() - 1);
    }
}
