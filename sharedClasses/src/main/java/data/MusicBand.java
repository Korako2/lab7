package data;

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
    private static final long serialVersionUID = 1L;
    @Getter
    private long id;
    @Getter
    private final String name;
    @Getter
    private final Coordinates coordinates;
    @Getter
    private final ZonedDateTime creationDate;
    @Getter
    private final Long numberOfParticipants;
    @Getter
    private final long albumsCount;
    @Getter
    private final String description;
    @Getter
    private final MusicGenre genre;
    @Getter
    private final Person frontMan;

    public String toString() {
        return String.format("ID: %s, name: %s, coordinates: %s, creation date: %s,number of participants: %s " +
                        "albums count: %s, description: %s, genre: %s, %s", id, name, coordinates, getStringDate(), numberOfParticipants,
                albumsCount, description, genre, frontMan);
    }

    /**
     * A method for getting a string representation of a collection item to write to the collection.
     *
     * @return String representation of the element.
     */
    public String getStringToSaveInFile() {

        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s", id, name, coordinates.getStringToSaveInFile(), getStringDate(), numberOfParticipants,
                albumsCount, description, genre, frontMan.getStringToSaveInFile());
    }

    /**
     * A method for getting a string representation of a date.
     *
     * @return String representation of the date.
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

