package collection.collectionUtil;

import IOutils.fileUtils.FileManager;
import collection.MusicBand;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CollectionStorage {
    HashSet<MusicBand> musicBands;
    HashSet<Long> IDSet;

    public boolean fillCollection(String file) {
        FileManager fileManager = new FileManager();
        musicBands = fileManager.readCollection(file);
        for (MusicBand musicBand : musicBands) {
            IDSet.add(musicBand.getId());
        }
        return musicBands != null;
    }

    public long generateID() {
        long id;
        if (Collections.max(IDSet) == Long.MAX_VALUE) {
            id = 1;
        } else {
            id = Collections.max(IDSet) + 1;
        }
        while (!IDSet.add(id)) {
            id += 1;
        }
        return id;
    }

    public HashSet<MusicBand> getMusicBands() {
        return musicBands;
    }
}
