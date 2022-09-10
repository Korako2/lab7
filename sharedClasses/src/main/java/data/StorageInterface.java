package data;

import commands.commandsUtils.CommandResult;

import java.io.IOException;
import java.rmi.AccessException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Set;

/**
 * interface for classes that store a collection.
 *
 * @param <T> type of collection items.
 */
public interface StorageInterface<T> {
    void fillCollection(MusicBand musicBand) throws IOException, ParseException, NumberFormatException;
    MusicBand getMinObject();
    void add(T t, String userName) throws SQLException;
    void clear(String userName) throws SQLException;
    String show();
    List<MusicBand> FilterLessThanNumberOfParticipants(Long numberOfParticipants);
    Set<MusicGenre> PrintUniqueGenre();
    Set<Long> getIdByLower(MusicBand musicBand, String userName);
    Set<MusicBand> getMusicBandsOfDescription(String description);
    String getInfo();
    boolean removeById(long id, String userName) throws SQLException, AccessException;
    boolean update(long id, MusicBand musicBand, String userName) throws SQLException, AccessException;

}

