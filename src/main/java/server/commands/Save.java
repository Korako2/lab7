package server.commands;

import server.collectionUtil.CollectionManager;
import sharedClasses.commands.Command;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * A class for saving a collection to a file.
 */
public class Save extends Command<ArgObjectForServer> {
    public Save() {
        super(false, 0, "SAVE", "to save the collection to a file", true);
    }

    @Override
    public String execute(ArgObjectForServer argObject) {
        String result = "The collection was saved";
        try {
            argObject.getCollectionManager().saveCollection();
        } catch (FileNotFoundException e) {
            result = "This file wasn't found";
        } catch (SecurityException e) {
            result = "Write access to the file is denied";
        } catch (IOException e) {
            result = "Some I/O errors occur";
        }
        return result;
    }
}
