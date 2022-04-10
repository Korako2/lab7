package collection.collectionUtil;


import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;

/**
 * Интерфейс для классов, которые хранят коллекцию.
 *
 * @param <T> тип элементов коллекции.
 */
public interface StorageInterface<T> {

    boolean fillCollection(String file) throws IOException, ParseException;

    void saveCollection() throws IOException;

    T getMinObject();

    void add(T element);

    void clear();

    //HashSet<T> getCollection();

    Date getDate();

    boolean removeById(long id);

}

