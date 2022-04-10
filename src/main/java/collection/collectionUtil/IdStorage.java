package collection.collectionUtil;

import collection.MusicBand;
import lombok.Getter;

import java.util.Collections;
import java.util.HashSet;

public class IdStorage {
    @Getter
    private final HashSet<Long> iDSet;
    public IdStorage() {
        iDSet = new HashSet<>();
    }

    public boolean addID(MusicBand musicBand) {
        return iDSet.add(musicBand.getId());
    }
    /**
     * Метод для генерации ID для нового элемента коллекции, добавленного из консоли или скрипа.
     *
     * @return сгенерированное ID.
     */

    public long generateID() {
        long id;
        if (iDSet.isEmpty()) return 1;
        if (Collections.max(iDSet) == Long.MAX_VALUE) {
            id = 1;
        } else {
            id = Collections.max(iDSet) + 1;
        }
        while (!iDSet.add(id)) {
            id += 1;
        }
        return id;
    }
}
