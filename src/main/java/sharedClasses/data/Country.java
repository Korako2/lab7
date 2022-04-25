package sharedClasses.data;

import java.io.Serializable;

public enum Country implements Serializable {
    GERMANY("Германия"),
    INDIA("Индия"),
    VATICAN("Ватикан");

    private final String country;

    Country(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
}
