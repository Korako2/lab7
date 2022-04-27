package data;

import java.io.Serializable;

public enum Country implements Serializable {
    GERMANY("Германия"),
    INDIA("Индия"),
    VATICAN("Ватикан");
    private static final long serialVersionUID = 1L;
    private final String country;

    Country(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
}
