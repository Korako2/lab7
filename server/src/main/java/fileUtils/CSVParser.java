package fileUtils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import data.MusicBand;

import java.io.*;

/**
 * A class for parsing a collection from a file.
 */
class CSVParser {
    /**
     * A method that parses a collection from a file.
     *
     * @param file the name of the file containing the collection.
     * @return HashSet<MusicBand> collection with objects {@link MusicBand}.
     * @throws IOException in case of an error reading from the file.
     **/
    public Iterable<CSVRecord> parse(String file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        Reader reader = new BufferedReader(new InputStreamReader(bufferedInputStream));
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
        return records;
    }

}

