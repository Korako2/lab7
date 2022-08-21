package data;

import lombok.Getter;

import java.io.Serializable;

public enum EyeColor implements Serializable {
    RED("RED"),
    WHITE("WHITE"),
    BROWN("BROWN");
    private static final long serialVersionUID = 1L;
    @Getter
    private final String color;

    EyeColor(String color) {
        this.color = color;
    }
}

