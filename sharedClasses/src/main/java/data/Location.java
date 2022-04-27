package data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
public class Location implements Serializable {
    private static final long serialVersionUID = 1L;
    @Getter
    private final Float x;
    @Getter
    private final int y;
    @Getter
    private final Long z;

    public String toString() {
        return String.format("location: x = %s, y = %s, z = %s", x, y, z);
    }

    public String getStringToSaveInFile() {
        return String.format("%s,%s,%s", x, y, z);
    }
}
