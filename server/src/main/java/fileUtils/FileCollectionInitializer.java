package fileUtils;

import collectionUtils.MusicBandBuilder;
import collectionUtil.ObjectValidation;
import org.apache.commons.csv.CSVRecord;
import data.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;

/**
 * A class for initializing the collection.
 */
public class FileCollectionInitializer {
    private final HashSet<MusicBand> musicBands = new HashSet<>();

    public HashSet<MusicBand> initializeCollection(Iterable<CSVRecord> records) throws ParseException {
        for (CSVRecord record : records) {
            MusicBand OneMusicBand = createMusicBand(record);
            musicBands.add(OneMusicBand);
        }
        for (MusicBand OneMusicBand : musicBands) {
            ObjectValidation objectValidation = new ObjectValidation();
            if (!objectValidation.checkObject(OneMusicBand)) {
                return null;
            }
        }
        return musicBands;
    }

    /**
     * A method for creating an object based on the field values from a file.
     *
     * @param record the values of the object fields received from the CSV parser.
     * @return object {@link MusicBand}.
     * @throws ParseException in the case when the format of the fields in the file is incorrect.
     */
    public MusicBand createMusicBand(CSVRecord record) throws ParseException {
        long id = Long.parseLong(record.get("id"));
        String bandName = record.get("bandName");
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ZonedDateTime creationDate = Instant.ofEpochMilli(formatter.parse(record.get("creationDate")).getTime()).atZone(ZoneId.systemDefault());
        Long numberOfParticipants = Long.parseLong(record.get("numberOfParticipants"));
        long albumsCount = Long.parseLong(record.get("albumsCount"));
        String description = record.get("description");
        MusicGenre genre = MusicGenre.valueOf(record.get("genre").toUpperCase());

        Coordinates coordinates = createCoordinates(record);
        Person person = createPerson(record);
        MusicBandBuilder musicBandBuilder = new MusicBandBuilder();

        return musicBandBuilder.setId(id)
                .setName(bandName)
                .setAlbumsCount(albumsCount)
                .setCoordinates(coordinates)
                .setCreationDate(creationDate)
                .setDescription(description)
                .setGenre(genre)
                .setFrontMan(person)
                .setNumberOfParticipants(numberOfParticipants).build();
    }

    private Coordinates createCoordinates(CSVRecord record) {
        Float x = Float.parseFloat(record.get("x"));
        int y = Integer.parseInt(record.get("y"));
        return new Coordinates(x, y);
    }

    private Location createLocation(CSVRecord record) {
        Float personX = Float.parseFloat(record.get("personX"));
        int personY = Integer.parseInt(record.get("personY"));
        Long personZ = Long.parseLong(record.get("personZ"));
        return new Location(personX, personY, personZ);
    }

    private Person createPerson(CSVRecord record) {
        String personName = record.get("personName");
        Double height;
        if (record.get("height").equals("")) {
            height = null;
        } else {
            height = Double.parseDouble(record.get("height"));
        }
        EyeColor eyeColor;
        if (record.get("eyeColor").equals("")) {
            eyeColor = null;
        } else {
            eyeColor = EyeColor.valueOf(record.get("eyeColor").toUpperCase());
        }
        HairColor hairColor = HairColor.valueOf(record.get("hairColor").toUpperCase());
        Country nationality = Country.valueOf(record.get("nationality").toUpperCase());
        Location location = createLocation(record);
        return new Person(personName, height, eyeColor, hairColor, nationality, location);
    }
}
