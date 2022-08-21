package IOutils;

import java.util.*;

import static clientApp.App.out;

public class Proceed {
    private final Scanner input;
    private final Map<String, Boolean> answerMap = new HashMap<>();

    public Proceed(Scanner input) {
        this.input = input;
        answerMap.put("yes", true);
        answerMap.put("y", true);
        answerMap.put("ye", true);
        answerMap.put("n", false);
        answerMap.put("no", false);
    }

    public boolean requestResponse(String question, String defaultAnswer) throws IllegalArgumentException {
        checkDefaultAnswer(question, defaultAnswer);
        while (true) {
            String answer = null;
            try {
                answer = input.nextLine().toLowerCase(Locale.ROOT);
            } catch (NoSuchElementException e) {
                System.exit(-1);
            }
            if (answerMap.get(answer) != null) return answerMap.get(answer);
            out.println("Write yes or no.");
        }
    }

    private void checkDefaultAnswer(String question, String defaultAnswer) {
        if (defaultAnswer.equals("yes")) {
            out.print(question + " [Y/n]: ");
            answerMap.put("", true);
        } else {
            if (defaultAnswer.equals("no")) {
                out.print(question + " [y/N]: ");
                answerMap.put("", false);
            } else {
                throw new IllegalArgumentException();
            }
        }
    }
}
