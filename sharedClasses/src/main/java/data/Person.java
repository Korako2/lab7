package data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * A class for the group's frontMan object.
 */
@RequiredArgsConstructor
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    @Getter
    private final String name;
    @Getter
    private final Double height;
    @Getter
    private final EyeColor eyeColor;
    @Getter
    private final HairColor hairColor;
    @Getter
    private final Country nationality;
    @Getter
    private final Location location;

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
