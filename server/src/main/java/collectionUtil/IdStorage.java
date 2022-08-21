package collectionUtil;

import data.MusicBand;
import lombok.Getter;

import java.util.Collections;
import java.util.HashSet;

/**
 * A class for storing object ID.
 */
public class IdStorage {
    @Getter //todo
    private final HashSet<Long> iDSet;

    public IdStorage() {
        iDSet = new HashSet<>();
    }

    public boolean addId(MusicBand musicBand) {
        return iDSet.add(musicBand.getId());
    }

    /**
     * A method for generating an ID for a new collection item added from the console or script.
     *
     * @return generated ID.
     */

    public long generateId() {
        long id;
        if (iDSet.isEmpty()) return 1;
        if (collectionIsOver()) {
            id = 1;
        } else {
            id = Collections.max(iDSet) + 1;
        }
        while (!iDSet.add(id)) {
            id += 1;
        }
        return id;
    }
    private boolean collectionIsOver() {return Collections.max(iDSet) == Long.MAX_VALUE;}
}
