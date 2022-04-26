package server.collectionUtil;


import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * interface for classes that store a collection.
 *
 * @param <T> type of collection items.
 */
public interface StorageInterface<T> {

    boolean fillCollection(String file) throws IOException, ParseException;

    void saveCollection() throws IOException;

    T getMinObject();

    void add(T element);

    void clear();

    Date getDate();

    boolean removeById(long id);

}

