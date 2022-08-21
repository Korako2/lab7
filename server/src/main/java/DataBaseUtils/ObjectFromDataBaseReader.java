package DataBaseUtils;

import collectionUtils.MusicBandBuilder;
import data.*;
import lombok.RequiredArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
public class ObjectFromDataBaseReader {
    private final ResultSet resultSet;

    public MusicBand readObject() throws SQLException {
        LocalDate date = LocalDate.parse(resultSet.getString("creation_date"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        ZonedDateTime zonedDateTime = date.atStartOfDay(ZoneId.systemDefault());
        MusicBandBuilder musicBandBuilder = new MusicBandBuilder();
        return musicBandBuilder.setId(resultSet.getLong("id"))
                .setName(resultSet.getString("band_name"))
                .setCoordinates(readCoordinates())
                .setCreationDate(zonedDateTime)
                .setNumberOfParticipants(resultSet.getLong("number_of_participants"))
                .setAlbumsCount(resultSet.getLong("albums_count"))
                .setDescription(resultSet.getString("description"))
                .setGenre(MusicGenre.valueOf(resultSet.getString("genre")))
                .setFrontMan(readPerson()).build();
    }
    private Coordinates readCoordinates() throws SQLException {
        return new Coordinates(resultSet.getFloat("x"), resultSet.getInt("y"));
    }
    private Person readPerson() throws SQLException {
        return new Person(resultSet.getString("person_name"), resultSet.getDouble("height"),
                EyeColor.valueOf(resultSet.getString("eye_color").toUpperCase()),
                HairColor.valueOf(resultSet.getString("hair_color").toUpperCase()),
                Country.valueOf(resultSet.getString("nationality")),
                readLocation());
    }

    private Location readLocation() throws SQLException {
        return new Location(resultSet.getFloat("person_x"), resultSet.getInt("person_y"),
                resultSet.getLong("person_z"));
    }
}
