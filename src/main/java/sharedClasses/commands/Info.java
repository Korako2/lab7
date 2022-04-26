package sharedClasses.commands;

import server.commands.ArgObjectForServer;

/**
 * A class for displaying information about the collection.
 */
public class Info extends Command<ArgObjectForServer> {
    public Info() {
        super(false, 0, "INFO", "write to the standard output information about the collection of (type, date of initialization, the number of elements, etc.)", true);
    }

    @Override
    public String execute(ArgObjectForServer argObject) {
        return argObject.getCollectionManager().getInfo();
    }
}
