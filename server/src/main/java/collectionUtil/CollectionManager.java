package collectionUtil;

import DataBaseUtils.DataBaseControl;
import fileUtils.FileManager;
import data.StorageInterface;
import lombok.Getter;
import data.MusicBand;
import data.MusicGenre;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A class for storing a collection.
 */

public class CollectionManager implements StorageInterface<MusicBand> {
    /**
     * Collection with elements.
     */
    @Getter
    public Collection<MusicBand> musicBands = Collections.synchronizedCollection(new HashSet<>()); //todo поменять на private
    /**
     * Collection for storing the ID of the elements of the main collection.
     */
    @Getter
    private Date date;
    private String file;
    private final FileManager fileManager;
    private final IdStorage idStorage;
    private final DataBaseControl dataBaseControl;

    public CollectionManager(DataBaseControl dataBaseControl) {

        fileManager = new FileManager();
        idStorage = new IdStorage();
        this.dataBaseControl = dataBaseControl;
    }

    public void fillCollection(MusicBand musicBand) {
         musicBands.add(musicBand);//todo :)
    }

    /**
     * Method for saving the collection to a file.
     *
     * @throws IOException if an error occurs when writing to a file.
     */
    public void saveCollection() throws IOException {
        fileManager.writeCollection(file, musicBands);
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

    public void add(MusicBand musicBand, String userName) throws SQLException {
        MusicBand musicBandFromDB = dataBaseControl.addToDataBase(musicBand, userName);
        musicBands.add(musicBandFromDB);
    }

    public void clear() {
        musicBands.clear();
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

    public Set<Long> getIdByLower(MusicBand musicBand) {
        return musicBands.stream().filter(p -> musicBand.compareTo(p) > 0).map(MusicBand::getId).collect(Collectors.toSet());
    }

    public Set<MusicBand> getMusicBandsOfDescription(String description) {
        return musicBands.stream().filter(p -> p.getDescription().indexOf(description) == 0).collect(Collectors.toSet());
    }

    public String getInfo() {
        return "Collection type : " + musicBands.getClass().getName() + ", date of creation: "
                + getDate() + ", number of objects: " + musicBands.size();
    }

    public long generateId() {
        return idStorage.generateId();
    }

    /**
     * Method for deleting a collection item by ID.
     *
     * @param id of the object to delete.
     * @return true if the object was successfully deleted; otherwise false.
     */
    public boolean removeById(long id, String userName) throws SQLException {
        musicBands.removeIf(musicBand -> musicBand.getId() == id);
        return dataBaseControl.removeById(id, userName);
    }
}
