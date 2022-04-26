package sharedClasses.commands;

import server.commands.ArgObjectForServer;

/**
 * A class for displaying all elements of a collection in a string representation.
 */
public class Show extends Command<ArgObjectForServer> {

    public Show() {
        super(false, 0, "SHOW", "print to standard output all the elements of the collection in the string representation", true);
    }

    public String execute(ArgObjectForServer argObject) {
        return argObject.getCollectionManager().show();
    }
}
