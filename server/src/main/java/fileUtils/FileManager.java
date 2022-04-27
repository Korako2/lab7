package fileUtils;

import data.MusicBand;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;

/**
 * A class for reading and writing a collection to a file.
 */
public class FileManager {
    CSVParser csvParser = new CSVParser();
    FileCollectionInitializer fileCollectionInitializer = new FileCollectionInitializer();

    /**
     * A method for reading a collection from a file.
     *
     * @param file the name of the file containing the collection.
     * @return read collection with objects {@link MusicBand}
     */
    public HashSet<MusicBand> readCollection(String file) throws IOException, ParseException {
        Iterable<CSVRecord> records = csvParser.parse(file);
        return fileCollectionInitializer.initializeCollection(records);
    }

    /**
     * A method for writing a collection to a file.
     *
     * @param fileName   the file name.
     * @param musicBands collection.
     * @throws IOException in case of errors in writing to the file.
     */
    public void writeCollection(String fileName, HashSet<MusicBand> musicBands) throws IOException {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(fileName);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File is accessible.");
        }
        byte[] buffer = ("id,bandName,x,y,creationDate,numberOfParticipants,albumsCount,description,genre,personName," +
                "height,eyeColor,hairColor,nationality,personX,personY,personZ\n").getBytes();
        fileOutputStream.write(buffer, 0, buffer.length);
        for (MusicBand i : musicBands) {
            buffer = (i.getStringToSaveInFile() + "\n").getBytes();
            fileOutputStream.write(buffer, 0, buffer.length);
        }
        fileOutputStream.close();
    }
}
