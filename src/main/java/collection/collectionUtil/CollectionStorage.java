package collection.collectionUtil;

import IOutils.fileUtils.FileManager;
import collection.MusicBand;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

public class CollectionStorage implements StorageInterface<MusicBand> {
    private HashSet<MusicBand> musicBands;
    private HashSet<Long> IDSet;
    private Date date;
    private String file;
    private final FileManager fileManager;

    public CollectionStorage() {
        fileManager = new FileManager();
    }

    public boolean fillCollection(String file) {
        this.file = file;
        musicBands = fileManager.readCollection(file);
        if (musicBands == null) return false;
        date = new Date();
        IDSet = new HashSet<>();
        for (MusicBand musicBand : musicBands) {
            IDSet.add(musicBand.getId());
        }
        return true;
    }

    public void saveCollection() throws IOException {
        fileManager.writeCollection(file, musicBands);
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

    public HashSet<MusicBand> getCollection() {
        return musicBands;
    }

    public Date getDate() {
        return date;
    }

    public boolean removeById(long id) {
        return musicBands.removeIf(musicBand -> musicBand.getId() == id);
    }
}
