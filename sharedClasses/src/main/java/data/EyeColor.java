package data;

import java.io.Serializable;

public enum EyeColor implements Serializable {
    RED("красный"),
    WHITE("белый"),
    BROWN("коричневый");
    private static final long serialVersionUID = 1L;
    private final String color;

    EyeColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}

