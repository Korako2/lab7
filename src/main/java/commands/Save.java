package commands;

import commands.commandsUtils.ArgObject;
import commands.commandsUtils.NameOfCommands;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Save extends Command {
    public Save() {
        super(false, 0, NameOfCommands.SAVE, "to save the collection to a file");
    }

    @Override
    public String execute(ArgObject argObject) {
        String result = "The collection was saved";
        try {
            argObject.getCollectionStorage().saveCollection();
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
