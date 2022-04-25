package sharedClasses.data;

import java.io.Serializable;

public enum HairColor implements Serializable {
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
