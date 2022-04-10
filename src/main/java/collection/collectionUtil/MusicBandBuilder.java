package collection.collectionUtil;

import collection.Coordinates;
import collection.MusicBand;
import collection.MusicGenre;
import collection.Person;


import java.time.ZonedDateTime;

public class MusicBandBuilder {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long numberOfParticipants; //Поле может быть null, Значение поля должно быть больше 0
    private long albumsCount; //Значение поля должно быть больше 0
    private String description; //Поле не может быть null
    private MusicGenre genre; //Поле может быть null
    private Person frontMan; //Поле может быть null

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
