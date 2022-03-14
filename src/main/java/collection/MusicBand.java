package collection;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Класс для элементов коллекции.
 */
public class MusicBand {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private final String name; //Поле не может быть null, Строка не может быть пустой
    private final Coordinates coordinates; //Поле не может быть null
    private final ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private final Long numberOfParticipants; //Поле может быть null, Значение поля должно быть больше 0
    private final long albumsCount; //Значение поля должно быть больше 0
    private final String description; //Поле не может быть null
    private final MusicGenre genre; //Поле может быть null
    private final Person frontMan; //Поле может быть null

    public MusicBand(long id, String name, Coordinates coordinates, ZonedDateTime creationDate, Long numberOfParticipants,
                     long albumsCount, String description, MusicGenre genre, Person frontMan) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.numberOfParticipants = numberOfParticipants;
        this.albumsCount = albumsCount;
        this.description = description;
        this.genre = genre;
        this.frontMan = frontMan;
    }

    public MusicBand(String name, Coordinates coordinates, ZonedDateTime creationDate, Long numberOfParticipants,
                     long albumsCount, String description, MusicGenre genre, Person frontMan) {
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.numberOfParticipants = numberOfParticipants;
        this.albumsCount = albumsCount;
        this.description = description;
        this.genre = genre;
        this.frontMan = frontMan;
    }

    public String toString() {
        return "ID: " + id + ", name: " + name + ", coordinates: x = " + coordinates.getX() +
                ", y = " + coordinates.getY() + ", creation date: " + getStringDate()
                + ", number of participants: " + numberOfParticipants + ", albums count: " + albumsCount +
                ", description: " + description + ", genre: " + genre + ", name of front man: " +
                frontMan.getName() + ", height: " + frontMan.getHeight() + ", eye color: " +
                frontMan.getEyeColor() + ", hair color: " + frontMan.getHairColor()
                + ", nationality: " + frontMan.getNationality() + ", location: x = "
                + frontMan.getLocation().getX() + ", y = " + frontMan.getLocation().getY() + ", z = "
                + frontMan.getLocation().getZ();
    }

    /**
     * Метод для получения строкового представления элемента коллекции для записи в коллекцию.
     *
     * @return String представление элемента.
     */
    public String getStringToSaveInFile() {
        String height;
        String eyeColor;
        if (frontMan.getHeight() == null) {
            height = "";
        } else {
            height = String.valueOf(frontMan.getHeight());
        }
        if (frontMan.getEyeColor() == null) {
            eyeColor = "";
        } else {
            eyeColor = String.valueOf(frontMan.getEyeColor());
        }
        return id + "," + name + "," + coordinates.getX() + "," + coordinates.getY() + "," + getStringDate()
                + "," + numberOfParticipants + "," + albumsCount + "," + description + "," + genre
                + "," + frontMan.getName() + "," + height + "," + eyeColor
                + "," + frontMan.getHairColor() + "," + frontMan.getNationality()
                + "," + frontMan.getLocation().getX() + "," + frontMan.getLocation().getY() + ","
                + frontMan.getLocation().getZ();
    }

    /**
     * Метод для получения строкового представления даты.
     *
     * @return String представление даты.
     */
    public String getStringDate() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(creationDate);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public Long getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public long getAlbumsCount() {
        return albumsCount;
    }

    public String getDescription() {
        return description;
    }

    public MusicGenre getGenre() {
        return genre;
    }

    public Person getFrontMan() {
        return frontMan;
    }

    public void setId(long id) {
        this.id = id;
    }
}

