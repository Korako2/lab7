package client.commands;

import client.Client;
import client.IOutils.UserInputManager;
import sharedClasses.Request;
import sharedClasses.Response;
import sharedClasses.commands.Command;
import sharedClasses.commands.commandsUtils.ArgObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс для считывание и исполнения скрипта из указанного файла.
 */
public class ExecuteScript extends Command {
    private Client client;
    private HashSet<String> fileNames;

    public ExecuteScript(Client client) {
        super(false, 1, "EXECUTE_SCRIPT", "read and execute the script from the specified file. The script contains commands in the same form in which they are entered by the user in\n" +
                "interactive mode.", false);
        this.client = client;
        fileNames = new HashSet<>();
    }

    public String execute(ArgObject argObject) {
        UserInputManager inputFromFile;
        try {
            FileReader fileReader = new FileReader(argObject.getArgs()[1]);
            inputFromFile = new UserInputManager((ClientCommandsManager)argObject.getManager(), new Scanner(fileReader),
                    false, new PrintStream(System.out));
        } catch (FileNotFoundException e) {
            return "Wrong file";
        }
        File script = new File(argObject.getArgs()[1]);
        if (!fileNames.add(script.getAbsolutePath()))
            return "There is a loop in scripts! Execute_script wasn't executed, it was skipped.";
        String result = "Script in file " + argObject.getArgs()[1] + " was executed";
        Request continueFlag;
        do {
            try {
                continueFlag = inputFromFile.input();
                client.getWriter().writeObject(continueFlag);
                client.getReader().readObject();
            } catch (NoSuchElementException e) {
                result  = e.getMessage() + " (wrong input of command/object in script).";
                continueFlag = null;
            } catch (NumberFormatException e) {
                result = e.getMessage() + " (wrong input of object in script).";
                continueFlag = null;
            } catch (IllegalArgumentException e) {
                result = e.getMessage() + " (in script detected some unknown command)";
                continueFlag = null;
            } catch (Exception e) {
                result = "Some exception during script execution: " + e.getMessage();
                continueFlag = null;
            }
        } while (continueFlag != null);
        fileNames.remove(script.getAbsolutePath());
        return result;
    }
}


