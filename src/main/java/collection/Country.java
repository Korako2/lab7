package collection;

public enum Country {
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
