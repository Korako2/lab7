package sharedClasses.commands;

import server.commands.ArgObjectForServer;

/**
 * A class for cleaning the collection.
 */
public class Clear extends Command<ArgObjectForServer> {

    public Clear() {
        super(false, 0, "CLEAR", "to clear the collection", true);
    }

    public String execute(ArgObjectForServer argObject) {
        argObject.getCollectionManager().clear();
        return "Collection is empty now!";
    }
}
