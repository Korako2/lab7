package commands;

import IOutils.UserInputManager;
import clientApp.Client;
import commands.commandsUtils.ArgObjectForClient;
import commands.commandsUtils.CommandResult;
import messageUtils.ResponseCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class for reading and executing a script from a specified file.
 */
public class ExecuteScript extends Command<ArgObjectForClient> {
    private final Client client;
    private final HashSet<String> fileNames;

    public ExecuteScript(Client client) {
        super(false, 1, "EXECUTE_SCRIPT", "read and execute the script from the specified file. The script contains commands in the same form in which they are entered by the user in\n" +
                "interactive mode.", false);
        this.client = client;
        fileNames = new HashSet<>();
    }

    public CommandResult execute(ArgObjectForClient argObject) {
        UserInputManager inputFromFile;
        try {
            FileReader fileReader = new FileReader(argObject.getArgs()[1]);
            inputFromFile = new UserInputManager(argObject.getClientCommandManager(), new Scanner(fileReader),
                    false, new PrintStream(System.out));
        } catch (FileNotFoundException e) {
            return new CommandResult("Wrong file", ResponseCode.ERROR);
        }
        File script = new File(argObject.getArgs()[1]);
        if (!fileNames.add(script.getAbsolutePath())) {
            return new CommandResult("There is a loop in scripts! Execute_script wasn't executed, it was skipped.", ResponseCode.ERROR);
        }
        String result = "Script in file " + argObject.getArgs()[1] + " was executed";
        boolean resultOfRequest;
        do {
            try {
                resultOfRequest = client.requestToServer(inputFromFile);
            } catch (NoSuchElementException e) {
                return new CommandResult(e.getMessage() + " (wrong input of command/object in script).", ResponseCode.ERROR);
            } catch (NumberFormatException e) {
                return new CommandResult(e.getMessage() + " (wrong input of object in script).", ResponseCode.ERROR);
            } catch (IllegalArgumentException e) {
                return new CommandResult(e.getMessage() + " (in script detected some unknown command)", ResponseCode.ERROR);
            } catch (Exception e) {
                return new CommandResult("Some exception during script execution: " + e.getMessage(), ResponseCode.ERROR);
            }
        } while (resultOfRequest);
        fileNames.remove(script.getAbsolutePath());
        return new CommandResult(result, ResponseCode.OK);
    }
}


