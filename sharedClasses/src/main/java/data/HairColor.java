package data;

import lombok.Getter;

import java.io.Serializable;

public enum HairColor implements Serializable {
    RED("RED"),
    BLACK("BLACK"),
    BLUE("BLUE"),
    ORANGE("ORANGE"),
    WHITE("WHITE");

    private static final long serialVersionUID = 1L;
    @Getter
    private final String color;

    HairColor(String color) {
        this.color = color;
    }
}
