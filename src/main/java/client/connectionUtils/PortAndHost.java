package client.connectionUtils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PortAndHost {
    @Getter
    private final String host;
    @Getter
    private final int port;
}
