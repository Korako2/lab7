package collection.collectionUtil;


import java.io.IOException;
import java.util.Date;
import java.util.HashSet;

/**
 * Интерфейс для классов, которые хранят коллекцию.
 *
 * @param <T> тип элементов коллекции.
 */
public interface StorageInterface<T> {

    boolean fillCollection(String file);

    void saveCollection() throws IOException;

    long generateID();

    T getMinObject();

    void add(T element);

    void clear();

    HashSet<T> getCollection();

    Date getDate();

    boolean removeById(long id);

}

