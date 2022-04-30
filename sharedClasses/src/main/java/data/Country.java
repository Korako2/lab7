package data;

import lombok.Getter;

import java.io.Serializable;

public enum Country implements Serializable {
    GERMANY("Германия"),
    INDIA("Индия"),
    VATICAN("Ватикан");
    private static final long serialVersionUID = 1L;
    @Getter
    private final String country;
    Country(String country) {
        this.country = country;
    }
}
