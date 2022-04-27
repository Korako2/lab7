package data;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Set;

/**
 * interface for classes that store a collection.
 *
 * @param <T> type of collection items.
 */
public interface StorageInterface<T> {
    boolean fillCollection(String file) throws IOException, ParseException, NumberFormatException;
    void saveCollection() throws IOException;
    MusicBand getMinObject();
    void add(T t);
    void clear();
    String show();
    List<MusicBand> FilterLessThanNumberOfParticipants(Long numberOfParticipants);
    Set<MusicGenre> PrintUniqueGenre();
    Set<Long> getIdByLower(MusicBand musicBand);
    Set<MusicBand> getMusicBandsOfDescription(String description);
    String getInfo();
    long generateId();
    boolean removeById(long id);

}

