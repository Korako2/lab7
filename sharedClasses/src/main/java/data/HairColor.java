package data;

import lombok.Getter;

import java.io.Serializable;

public enum HairColor implements Serializable {
    RED("красный"),
    BLACK("черный"),
    BLUE("голубой"),
    ORANGE("оранжевый"),
    WHITE("белый");

    private static final long serialVersionUID = 1L;
    @Getter
    private final String color;

    HairColor(String color) {
        this.color = color;
    }
}
