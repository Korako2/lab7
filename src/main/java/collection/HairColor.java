package collection;

public enum HairColor {
    RED("красный"),
    BLACK("черный"),
    BLUE("голубой"),
    ORANGE("оранжевый"),
    WHITE("белый");

    private final String color;

    HairColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
