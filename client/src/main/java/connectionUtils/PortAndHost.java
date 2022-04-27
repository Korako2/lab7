package connectionUtils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * A class for storing the host and port.
 */
@RequiredArgsConstructor
public class PortAndHost {
    @Getter
    private final String host;
    @Getter
    private final int port;
}
