package sharedClasses.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
public class Coordinates implements Serializable {
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
