package IOutils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Scanner;
import java.util.function.Function;

@RequiredArgsConstructor
public class InputAndOutput {
    private final Scanner scanner;
    @Getter
    private final boolean consoleReading;

    /**
     * A method for displaying an input prompt (in the case of working with the console) and reading the user's response.
     *
     * @param message a message for the user.
     * @return the user's response.
     */
    public String readLine(String message) {
        if (consoleReading) System.out.println(message);
        return scanner.nextLine();
    }

    /**
     * Read line and parse as value
     * <p>
     * If mapper throws it will print exception message and retries
     *
     * @param message prompt
     * @param mapper  mapping function
     * @param <T>     type of parsed value
     * @return mapped value
     */
    public <T> T readLineAs(String message, Function<String, T> mapper, Function<T, Boolean> condition) {
        while (true) {
            try {
                T input = mapper.apply(message);
                if (!condition.apply(input)) {
                    printLine("Wrong format of input!");
                } else return input;
            } catch (IllegalArgumentException e) {
                printLine("Wrong format of input!");
            }
            if (!consoleReading) throw new NumberFormatException("Wrong format of input in file.");
        }
    }

    /**
     * A method for displaying a message to the user (in the case of working with the console).
     *
     * @param message a message for the user.
     */
    public void printLine(String message) {
        if (consoleReading) System.out.println(message);
    }
}
