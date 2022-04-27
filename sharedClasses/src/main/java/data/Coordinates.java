package data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
public class Coordinates implements Serializable {
    private static final long serialVersionUID = 1L;
    @Getter
    private final Float x;
    @Getter
    private final int y;

    public String toString() {
        return String.format("coordinates: x = %s, y = %s", x, y);
    }

    public String getStringToSaveInFile() {
        return String.format("%s,%s", x, y);
    }

}
