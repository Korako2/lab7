package IOutils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Scanner;

@RequiredArgsConstructor
public class InputAndOutput {
    private final Scanner scanner;
    @Getter
    private final boolean showMessages;

    /**
     * A method for displaying an input prompt (in the case of working with the console) and reading the user's response.
     *
     * @param message a message for the user.
     * @return the user's response.
     */
    public String readLine(String message) {
        if (showMessages) System.out.println(message);
        return scanner.nextLine();
    }

    /**
     * A method for displaying a message to the user (in the case of working with the console).
     *
     * @param message a message for the user.
     */
    public void printLine(String message) {
        if (showMessages) System.out.println(message);
    }
}
