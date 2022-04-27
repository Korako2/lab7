package collectionUtils;

import data.Coordinates;
import data.MusicBand;
import data.MusicGenre;
import data.Person;


import java.time.ZonedDateTime;

/**
 * Class for creating a MusicBand object
 */
public class MusicBandBuilder {
    private long id;
    private String name;
    private Coordinates coordinates;
    private ZonedDateTime creationDate;
    private Long numberOfParticipants;
    private long albumsCount;
    private String description;
    private MusicGenre genre;
    private Person frontMan;

    public MusicBandBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public MusicBandBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public MusicBandBuilder setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
        return this;
    }

    public MusicBandBuilder setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public MusicBandBuilder setNumberOfParticipants(Long numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
        return this;
    }

    public MusicBandBuilder setAlbumsCount(long albumsCount) {
        this.albumsCount = albumsCount;
        return this;
    }

    public MusicBandBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public MusicBandBuilder setGenre(MusicGenre genre) {
        this.genre = genre;
        return this;
    }

    public MusicBandBuilder setFrontMan(Person frontMan) {
        this.frontMan = frontMan;
        return this;
    }

    public MusicBand build() {
        MusicBand musicBand = new MusicBand(id, name, coordinates, creationDate, numberOfParticipants,
                albumsCount, description, genre, frontMan);
        return musicBand;
    }
}
