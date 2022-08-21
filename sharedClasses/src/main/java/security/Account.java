package security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    @Getter
    private final String userName;
    @Getter
    private final String password;
    @Getter
    private final boolean isRegistered;

    // todo AccessLevel
}

