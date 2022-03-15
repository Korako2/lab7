package collection;

/**
 * Класс для объекта frontMan группы.
 */
public class Person {
    private final String name; //Поле не может быть null, Строка не может быть пустой
    private final Double height; //Поле может быть null, Значение поля должно быть больше 0
    private final EyeColor eyeColor; //Поле может быть null
    private final HairColor hairColor; //Поле не может быть null
    private final Country nationality; //Поле не может быть null
    private final Location location; //Поле может быть null

    public Person(String name, Double height, EyeColor eyeColor, HairColor hairColor, Country nationality, Location location) {
        this.name = name;
        this.height = height;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public Double getHeight() {
        return height;
    }

    public EyeColor getEyeColor() {
        return eyeColor;
    }

    public HairColor getHairColor() {
        return hairColor;
    }

    public Country getNationality() {
        return nationality;
    }

    public Location getLocation() {
        return location;
    }
}
