package connectionUtils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.PrintStream;

/**
 * A class for port and host validation.
 */
@RequiredArgsConstructor
public class AddressValidation {
    @Getter
    private String host;
    @Getter
    private int port;
    private final String[] hostAndPort;
    private final PrintStream out;
    private final int MIN_PORT = 0;
    private final int MAX_PORT = 65535;

    /**
     * A method for checking the host and port.
     *
     * @return true if the host and port are entered correctly; otherwise false.
     */
    public boolean checkAddress() {
        if (hostAndPort.length != 2) {
            out.println("Invalid input format. Specify host and port separated by a space after the jar-file name.");
            return false;
        }
        try {
            port = Integer.parseInt(hostAndPort[1]);
            host = hostAndPort[0];
            if (isValidPort()) return true;
            else {
                out.println("Wrong number for port");
                return false;
            }
        } catch (NumberFormatException e) {
            out.println("Port -  an integer number than or equal to zero.");
            return false;
        }
    }

    private boolean isValidPort() {return port >= MIN_PORT && port <= MAX_PORT;}


}
