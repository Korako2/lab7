package collection.collectionUtil;

import IOutils.fileUtils.FileManager;
import collection.MusicBand;

import java.util.HashSet;

public class CollectionStorage {
    HashSet<MusicBand> musicBands;
    public boolean fillCollection(String file) {
        FileManager fileManager = new FileManager();
        musicBands = fileManager.readCollection(file);
        return musicBands !=null;
    }

    public HashSet<MusicBand> getMusicBands() {
        return musicBands;
    }
}
