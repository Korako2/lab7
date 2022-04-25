package server.collectionUtil;

import client.IOutils.fileUtils.FileManager;
import sharedClasses.commands.commandsUtils.Manager;
import sharedClasses.data.MusicBand;
import sharedClasses.data.MusicGenre;
import lombok.Getter;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс для хранения коллекции.
 */

public class CollectionManager implements StorageInterface<MusicBand>, Manager {
    /**
     * Коллекция.
     */
    private HashSet<MusicBand> musicBands;
    /**
     * Коллекция для хранения ID элементов основной коллекции.
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
     * Метод для заполнения коллекции из файла.
     *
     * @param file имя файла с коллекцией.
     * @return true если заполнение коллекции из файла произошло удачно; иначе false.
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
     * Метод для сохранения коллекции в файл.
     *
     * @throws IOException если происходит ошибка при записи в файл.
     */
    public void saveCollection() throws IOException {
        fileManager.writeCollection(file, musicBands);
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
        return "Тип коллекции: " + musicBands.getClass().getName() + ", дата создания: "
                + getDate() + ", количество объектов: " + musicBands.size();
    }

    public long generateId() {
        return idStorage.generateID();
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
