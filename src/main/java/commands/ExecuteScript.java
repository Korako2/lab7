package commands;

import IOutils.UserInputManager;
import commands.commandsUtils.ArgObject;
import commands.commandsUtils.NameOfCommands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс для считывание и исполнения скрипта из указанного файла.
 */
public class ExecuteScript extends Command {
    HashSet<String> fileNames;

    public ExecuteScript() {
        super(false, 1, NameOfCommands.EXECUTE_SCRIPT, "read and execute the script from the specified file. The script contains commands in the same form in which they are entered by the user in\n" +
                "interactive mode.");
        fileNames = new HashSet<>();
    }

    public String execute(ArgObject argObject) {
        UserInputManager inputFromFile;
        try {
            FileReader fileReader = new FileReader(argObject.getArgs()[1]);
            inputFromFile = new UserInputManager(argObject.getCommandsManager(), new Scanner(fileReader),
                    argObject.getCollectionStorage(), false);
        } catch (FileNotFoundException e) {
            return "Wrong file";
        }
        File script = new File(argObject.getArgs()[1]);
        if (!fileNames.add(script.getAbsolutePath()))
            return "There is a loop in scripts! Execute_script wasn't executed, it was skipped.";
        String result = "Script in file " + argObject.getArgs()[1] + " was executed";
        boolean continueFlag;
        do {
            try {
                continueFlag = inputFromFile.input();
            } catch (NoSuchElementException e) {
                result = e.getMessage() + " (wrong input of command/object in script).";
                continueFlag = false;
            } catch (NumberFormatException e) {
                result = e.getMessage() + " (wrong input of object in script).";
                continueFlag = false;
            } catch (IllegalArgumentException e) {
                result = e.getMessage() + " (in script detected some unknown command)";
                continueFlag = false;
            } catch (Exception e) {
                result = "Some exception during script execution: " + e.getMessage();
                continueFlag = false;
            }
        } while (continueFlag);
        fileNames.remove(script.getAbsolutePath());
        return result;
    }
}
