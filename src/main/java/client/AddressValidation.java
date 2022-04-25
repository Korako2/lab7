package client;

import lombok.Getter;

import java.io.PrintStream;

public class AddressValidation {
    @Getter
    private String host;
    @Getter
    private int port;
    private String[] hostAndPort;
    PrintStream out;

    public AddressValidation(String[] hostAndPort, PrintStream out) {
        this.hostAndPort = hostAndPort;
        this.out = out;
    }

    public boolean checkAddress() {
        if (hostAndPort.length != 2) {
            out.println("Неверный формат ввода. Укажите host и port через пробел после имени jar-файла.");
            return false;
        }
        try {
            port = Integer.parseInt(hostAndPort[1]);
            host = hostAndPort[0];
        } catch (NumberFormatException e) {
            out.println("port - целое число больше или равно нулю.");
            return false;
        }
        return true;
    }

}
