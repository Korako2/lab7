package client;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Scanner;

@RequiredArgsConstructor
public class InputAndOutput {
    private final Scanner scanner;
    @Getter
    private final boolean showMessages;

    /**
     * Метод для вывода приглашения на ввод (в случае работы с консолью) и считывания ответа пользователя.
     *
     * @param message сообщение для пользователя.
     * @return ответ пользователя.
     */
    public String readLine(String message) {
        if (showMessages) System.out.println(message);
        return scanner.nextLine();
    }

    /**
     * Метод для вывода сообщения пользователю (в случае работы с консолью).
     *
     * @param message сообщение для пользователя.
     */
    public void printLine(String message) {
        if (showMessages) System.out.println(message);
    }
}
