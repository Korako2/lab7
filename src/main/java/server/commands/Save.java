package server.commands;

import server.collectionUtil.CollectionManager;
import sharedClasses.commands.Command;
import sharedClasses.commands.commandsUtils.ArgObject;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Класс для сохранения коллекции в файл.
 */
public class Save extends Command {
    public Save() {
        super(false, 0, "SAVE", "to save the collection to a file", true);
    }

    @Override
    public String execute(ArgObject argObject) {
        String result = "The collection was saved";
        try {
            ((CollectionManager)argObject.getManager()).saveCollection();
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
