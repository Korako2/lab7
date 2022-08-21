package data;

import lombok.Getter;

import java.io.Serializable;

public enum Country implements Serializable {
    GERMANY("GERMANY"),
    INDIA("INDIA"),
    VATICAN("VATICAN");
    private static final long serialVersionUID = 1L;
    @Getter
    private final String country;
    Country(String country) {
        this.country = country;
    }
}
