package collectionUtil;

import fileUtils.FileManager;
import data.StorageInterface;
import lombok.Getter;
import data.MusicBand;
import data.MusicGenre;

import java.io.IOException;
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
    private HashSet<MusicBand> musicBands;
    /**
     * Collection for storing the ID of the elements of the main collection.
     */
    @Getter
    private Date date;
    private String file;
    private final FileManager fileManager;
    private final IdStorage idStorage;

    public CollectionManager() {

        fileManager = new FileManager();
        idStorage = new IdStorage();
    }

    /**
     * A method to populate a collection from a file.
     *
     * @param file the name of the file with the collection.
     * @return true if filling in the collection from the file was successful; otherwise false.
     */

    public boolean fillCollection(String file) throws IOException, ParseException, NumberFormatException {
        this.file = file;
        try {
            musicBands = fileManager.readCollection(file);
        } catch (IOException e) {
            throw new IOException("Incorrect file name.");
        } catch (ParseException e) {
            throw new ParseException("Incorrect format of data in file.", e.getErrorOffset());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Incorrect data in file.");
        }

        if (musicBands == null) return false;
        date = new Date();
        for (MusicBand musicBand : musicBands) {
            if (!idStorage.addID(musicBand)) return false;
        }
        return true;
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

    public void add(MusicBand musicBand) {
        musicBands.add(musicBand);
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
        return idStorage.generateID();
    }

    /**
     * Method for deleting a collection item by ID.
     *
     * @param id of the object to delete.
     * @return true if the object was successfully deleted; otherwise false.
     */
    public boolean removeById(long id) {
        return musicBands.removeIf(musicBand -> musicBand.getId() == id);
    }
}
