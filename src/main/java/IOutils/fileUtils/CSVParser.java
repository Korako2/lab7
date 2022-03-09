package IOutils.fileUtils;

import collection.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

class CSVParser {
    public HashSet<MusicBand> parse(String file) throws IOException, ParseException {
        HashSet<MusicBand> musicBands = new HashSet<MusicBand>();
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        Reader reader = new BufferedReader(new InputStreamReader(bufferedInputStream));
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
        for (CSVRecord record : records) {
            MusicBand OneMusicBand = createMusicBand(record);
            musicBands.add(OneMusicBand);
            // System.out.println(id + bandName + x + y + creationDate + numberOfParticipants + albumsCount + description + genre + personName + height + eyeColor + hairColor + nationality + personX + personY + personZ);
        }
        return musicBands;
    }

    public MusicBand createMusicBand(CSVRecord record) throws ParseException {
        int id = Integer.parseInt(record.get("id"));
        String bandName = record.get("bandName");
        Float x = Float.parseFloat(record.get("x"));
        int y = Integer.parseInt(record.get("y"));
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        ZonedDateTime creationDate = Instant.ofEpochMilli(formatter.parse(record.get("creationDate")).getTime()).atZone(ZoneId.systemDefault());
        //ZonedDateTime creationDate = ZonedDateTime.parse(record.get("creationDate"));
        Long numberOfParticipants = Long.parseLong(record.get("numberOfParticipants"));
        long albumsCount = Long.parseLong(record.get("albumsCount"));
        String description = record.get("description");
        MusicGenre genre = MusicGenre.valueOf(record.get("genre").toUpperCase());
        String personName = record.get("personName");
        Double height = Double.parseDouble(record.get("height"));
        EyeColor eyeColor = EyeColor.valueOf(record.get("eyeColor").toUpperCase());
        HairColor hairColor = HairColor.valueOf(record.get("hairColor").toUpperCase());
        Country nationality = Country.valueOf(record.get("nationality").toUpperCase());
        Float personX = Float.parseFloat(record.get("personX"));
        int personY = Integer.parseInt(record.get("personY"));
        Long personZ = Long.parseLong(record.get("personZ"));
        Coordinates coordinates = new Coordinates(x, y);
        Location location = new Location(personX, personY, personZ);
        Person person = new Person(personName, height, eyeColor, hairColor, nationality, location);
        MusicBand musicBand = new MusicBand(id, bandName, coordinates, creationDate, numberOfParticipants,
                albumsCount, description, genre, person);
        return musicBand;
    }
}

