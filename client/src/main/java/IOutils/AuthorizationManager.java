package IOutils;

import security.Account;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

import static clientApp.App.out;

public class AuthorizationManager {
    private Proceed proceed;
    private Scanner scanner;
    private final String pepper;
    private MessageDigest messageDigest;

    public AuthorizationManager(Scanner scanner) throws NoSuchAlgorithmException {
        this.scanner = scanner;
        proceed = new Proceed(scanner);
        pepper = "Rsfn2141F";
        messageDigest = MessageDigest.getInstance("SHA-512");
    }

    public Account getAuthorizationData(boolean isWrongPassword) {
        boolean isRegistered = true;
        if (!isWrongPassword) isRegistered = proceed.requestResponse("Are you registered?", "yes");
        String login;
        while (true) {
            out.println("Enter login:");
            try {
                login = scanner.nextLine();
                if (login.length() < 6 || login.length() > 30)
                    out.println("The length of the login must be between 6 and 30 characters");
                else break;
            } catch (NoSuchElementException e) {
                System.exit(-1);
            }
        }
        String password;
        while (true) {
            out.println("Enter password: ");
            password = scanner.nextLine();
            if (password.length() < 6 || password.length() > 30)
                out.println("The length of the password must be between 6 and 30 characters");
            else break;
        }
        String encodedPassword = hash(password);
        return new Account(login, encodedPassword, isRegistered);
    }

    private String hash(String password) {
        String hash = pepper + password;
        byte[] encoded = messageDigest.digest(hash.getBytes(StandardCharsets.UTF_8));
        BigInteger bigInteger = new BigInteger(1, encoded);
        return bigInteger.toString(16);
    }

}
