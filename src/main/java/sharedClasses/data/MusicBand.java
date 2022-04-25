package sharedClasses.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Класс для элементов коллекции.
 */
@AllArgsConstructor
public class MusicBand implements Comparable<MusicBand>, Serializable {
    @Getter
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @Getter
    private final String name; //Поле не может быть null, Строка не может быть пустой
    @Getter
    private final Coordinates coordinates; //Поле не может быть null
    @Getter
    private final ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @Getter
    private final Long numberOfParticipants; //Поле может быть null, Значение поля должно быть больше 0
    @Getter
    private final long albumsCount; //Значение поля должно быть больше 0
    @Getter
    private final String description; //Поле не может быть null
    @Getter
    private final MusicGenre genre; //Поле может быть null
    @Getter
    private final Person frontMan; //Поле может быть null

    public String toString() {
        return String.format("ID: %s, name: %s, coordinates: %s, creation date: %s,number of participants: %s " +
                        "albums count: %s, description: %s, genre: %s, %s", id, name, coordinates, getStringDate(), numberOfParticipants,
                albumsCount, description, genre, frontMan);
    }

    /**
     * Метод для получения строкового представления элемента коллекции для записи в коллекцию.
     *
     * @return String представление элемента.
     */
    public String getStringToSaveInFile() {

        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s", id, name, coordinates.getStringToSaveInFile(), getStringDate(), numberOfParticipants,
                albumsCount, description, genre, frontMan.getStringToSaveInFile());
    }

    /**
     * Метод для получения строкового представления даты.
     *
     * @return String представление даты.
     */
    public String getStringDate() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(creationDate);
    }

    @Override
    public boolean equals(Object musicBand) {
        if (musicBand == this) {
            return true;
        }
        if (musicBand == null || getClass() != musicBand.getClass()) {
            return false;
        }
        MusicBand musicBand1 = (MusicBand) musicBand;

        return toString().equals(musicBand1.toString());
    }

    @Override
    public int compareTo(MusicBand o) {
        return Long.compare(this.getAlbumsCount(), o.getAlbumsCount());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public void setId(long id) {
        this.id = id;
    }
}

