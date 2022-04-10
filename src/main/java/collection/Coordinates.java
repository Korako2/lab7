package collection;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Coordinates {
    @Getter
    private final Float x; //Максимальное значение поля: 146, Поле не может быть null.
    @Getter
    private final int y;

    public String toString() {
        return String.format("coordinates: x = %s, y = %s", x, y);
    }

    public String getStringToSaveInFile() {
        return String.format("%s,%s", x, y);
    }

}
