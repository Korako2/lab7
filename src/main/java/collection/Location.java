package collection;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Location {
    @Getter
    private final Float x; //Поле не может быть null.
    @Getter
    private final int y;
    @Getter
    private final Long z; //Поле не может быть null.

    public String toString() {
        return String.format("location: x = %s, y = %s, z = %s", x, y, z);
    }

    public String getStringToSaveInFile() {
        return String.format("%s,%s,%s", x, y, z);
    }
}
