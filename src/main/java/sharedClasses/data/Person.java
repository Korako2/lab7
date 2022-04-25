package sharedClasses.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Класс для объекта frontMan группы.
 */
@RequiredArgsConstructor
public class Person implements Serializable {
    @Getter
    private final String name; //Поле не может быть null, Строка не может быть пустой
    @Getter
    private final Double height; //Поле может быть null, Значение поля должно быть больше 0
    @Getter
    private final EyeColor eyeColor; //Поле может быть null
    @Getter
    private final HairColor hairColor; //Поле не может быть null
    @Getter
    private final Country nationality; //Поле не может быть null
    @Getter
    private final Location location; //Поле может быть null

    public String toString() {
        return String.format("name of front man: %s, height: %s,  eye color: %s, hair color: %s, nationality: %s, %s",
                name, height, eyeColor, hairColor, nationality, location);
    }

    public String getStringToSaveInFile() {
        String heightToSave = height == null ? "" : String.valueOf(height);
        String eyeColorToSave = eyeColor == null ? "" : String.valueOf(eyeColor);
        return String.format("%s,%s,%s,%s,%s,%s", name, heightToSave, eyeColorToSave, hairColor, nationality, location.getStringToSaveInFile());
    }

}
