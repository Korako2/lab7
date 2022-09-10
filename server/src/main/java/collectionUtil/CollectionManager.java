package collectionUtil;

import dataBaseUtils.DataBaseControl;
import data.StorageInterface;
import lombok.Getter;
import data.MusicBand;
import data.MusicGenre;

import java.rmi.AccessException;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A class for storing a collection.
 */

public class CollectionManager implements StorageInterface<MusicBand> {
    /**
     * Collection with elements.
     */
    @Getter
    private final Collection<MusicBand> musicBands = Collections.synchronizedCollection(new HashSet<>());
    private final DataBaseControl dataBaseControl;

    public CollectionManager(DataBaseControl dataBaseControl) {
        this.dataBaseControl = dataBaseControl;
    }

    public void fillCollection(MusicBand musicBand) {
         musicBands.add(musicBand);
    }

    /**
     * A method for searching for a collection item with the minimum field albums Count.
     *
     * @return minimum object {@link MusicBand} from collection.
     */
    public MusicBand getMinObject() {
        MusicBand minMusicBand = null;
        long albumsCount = Long.MAX_VALUE;
        for (MusicBand i : musicBands) {
            if (i.getAlbumsCount() < albumsCount) {
                albumsCount = i.getAlbumsCount();
                minMusicBand = i;
            }
        }
        return minMusicBand;
    }

    public synchronized void add(MusicBand musicBand, String userName) throws SQLException {
        MusicBand musicBandFromDB = dataBaseControl.addToDataBase(musicBand, userName);
        musicBands.add(musicBandFromDB);
    }

    public synchronized void clear(String userName) throws SQLException {
        if (dataBaseControl.clear(userName)) {
            musicBands.removeIf(p -> p.getUserName().equals(userName));
        }
    }

    public String show() {
        if (musicBands.isEmpty()) {
            return "Collection is empty";
        }
        StringBuilder result = new StringBuilder();
        for (MusicBand i : musicBands) {
            result.append(i.toString()).append("\n");
        }
        return result.substring(0, result.toString().length() - 1);
    }

    public List<MusicBand> FilterLessThanNumberOfParticipants(Long numberOfParticipants) {
        return musicBands.stream().filter(p -> p.getNumberOfParticipants() < numberOfParticipants).collect(Collectors.toList());
    }

    public Set<MusicGenre> PrintUniqueGenre() {
        return musicBands.stream().map(MusicBand::getGenre).collect(Collectors.toSet());
    }

    public Set<Long> getIdByLower(MusicBand musicBand, String userName) {
        return musicBands.stream().filter(p -> musicBand.compareTo(p) > 0)
                .map(MusicBand::getId).collect(Collectors.toSet());
    }

    public Set<MusicBand> getMusicBandsOfDescription(String description) {
        return musicBands.stream().filter(p -> p.getDescription().indexOf(description) == 0).collect(Collectors.toSet());
    }

    public String getInfo() {
        return "Collection type : " + musicBands.getClass().getName() + ", number of objects: " + musicBands.size();
    }

    /**
     * Method for deleting a collection item by ID.
     *
     * @param id of the object to delete.
     * @return true if the object was successfully deleted; otherwise false.
     */
    public synchronized boolean removeById(long id, String userName) throws SQLException, AccessException {
        if (dataBaseControl.removeById(id, userName)) {
            return musicBands.removeIf(musicBand -> musicBand.getId() == id);
        }
        Stream<MusicBand> musicBand = musicBands.stream().filter(p -> p.getId() == id);
        if (musicBand.findAny().isPresent()) throw new AccessException("You do not have the rights to delete this element.");
        else return false;
    }
    public synchronized boolean update(long id, MusicBand musicBand, String userName) throws SQLException, AccessException {
        if (dataBaseControl.update(id, musicBand)) {
            musicBands.removeIf(p -> p.getId() == id);
            musicBand.setId(id);
            musicBands.add(musicBand);
            return true;
        }
        Stream<MusicBand> musicBandStream = musicBands.stream().filter(p ->  p.getId() == id);
        if (musicBandStream.findAny().isPresent()) throw new AccessException("You do not have the rights to delete this element.");
        else return false;
    }
}
