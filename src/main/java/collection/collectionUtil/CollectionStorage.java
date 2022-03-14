package collection.collectionUtil;

import IOutils.fileUtils.FileManager;
import collection.MusicBand;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

/**
 * Класс для хранения коллекции.
 */

public class CollectionStorage implements StorageInterface<MusicBand> {
    /**
     * Коллекция.
     */
    private HashSet<MusicBand> musicBands;
    /**
     * Коллекция для хранения ID элементов основной коллекции.
     */
    private HashSet<Long> IDSet;
    private Date date;
    private String file;
    private final FileManager fileManager;

    public CollectionStorage() {
        fileManager = new FileManager();
    }

    /**
     * Метод для заполнения коллекции из файла.
     *
     * @param file имя файла с коллекцией.
     * @return true если заполнение коллекции из файла произошло удачно; иначе false.
     */
    public boolean fillCollection(String file) {
        this.file = file;
        try {
            musicBands = fileManager.readCollection(file);
        } catch (IOException | ParseException e) {
            return false;
        }
        if (musicBands == null) return false;
        date = new Date();
        IDSet = new HashSet<>();
        for (MusicBand musicBand : musicBands) {
            IDSet.add(musicBand.getId());
        }
        return true;
    }

    /**
     * Метод для сохранения коллекции в файл.
     *
     * @throws IOException если происходит ошибка при записи в файл.
     */
    public void saveCollection() throws IOException {
        fileManager.writeCollection(file, musicBands);
    }

    /**
     * Метод для генерации ID для нового элемента коллекции, добавленного из консоли или скрипа.
     *
     * @return сгенерированное ID.
     */
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

    /**
     * Метод для поиска элемента коллекции с минимальным полем albumsCount.
     *
     * @return минимальный объект {@link MusicBand} из коллекции.
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

    public HashSet<MusicBand> getCollection() {
        return musicBands;
    }

    public Date getDate() {
        return date;
    }

    /**
     * Метод для удаления элемента коллекции по ID.
     *
     * @param id объекта, который нужно удалить.
     * @return true если объект успешно удален; иначе false.
     */
    public boolean removeById(long id) {
        return musicBands.removeIf(musicBand -> musicBand.getId() == id);
    }
}
