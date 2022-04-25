package client.IOutils.fileUtils;

import server.collectionUtil.MusicBandBuilder;
import server.collectionUtil.ObjectValidation;
import org.apache.commons.csv.CSVRecord;
import sharedClasses.data.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;

public class FileCollectionInitializer {
    private HashSet<MusicBand> musicBands = new HashSet<>();

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
        } //todo ?? Ok?
        return musicBands;
    }

    /**
     * Метод для создания объекта на основе значения полей из файла.
     *
     * @param record значения полей объекта, полученных от CSV парсера.
     * @return объект {@link MusicBand}.
     * @throws ParseException в случае, когда в файле формат полей некорректен.
     */
    public MusicBand createMusicBand(CSVRecord record) throws ParseException {
        long id = Long.parseLong(record.get("id")); //todo Разделить этот класс, слишком сложный.
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
