package IOutils.fileUtils;

import collection.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;

/**
 * Класс для парсинга коллекции из файла.
 */
class CSVParser {
    /**
     * Метод, осуществляющий парсинг коллекции из файла.
     *
     * @param file имя файла, содержащего коллекцию.
     * @return HashSet<MusicBand> коллекция с объектами {@link MusicBand}.
     * @throws IOException    в случае ошибки чтения из файла.
     * @throws ParseException в случае неверного формата данных в файле.
     */
    public Iterable<CSVRecord> parse(String file) throws IOException, ParseException {
        HashSet<MusicBand> musicBands = new HashSet<>();
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        Reader reader = new BufferedReader(new InputStreamReader(bufferedInputStream));
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
        return records;
    }

}

