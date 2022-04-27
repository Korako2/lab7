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

    /**
     * A method for checking the host and port.
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
            if (port <=0 || port >65535) {
                out.println("Wrong number for port");
                return false;
            }
        } catch (NumberFormatException e) {
            out.println("Port -  an integer number than or equal to zero.");
            return false;
        }
        return true;
    }

}
