package IOutils.fileUtils;

import collection.MusicBand;
import collection.collectionUtil.ObjectValidation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;

/**
 * Класс для чтения и записи коллекции в файл.
 */
public class FileManager {
    CSVParser csvParser = new CSVParser();

    /**
     * Метод для чтения коллекции из файла.
     *
     * @param file имя файла, содержажего коллекцию.
     * @return считанная коллекция с объектами {@link MusicBand}
     */
    public HashSet<MusicBand> readCollection(String file) {
        HashSet<MusicBand> musicBands = null;
        try {
            musicBands = csvParser.parse(file);
            for (MusicBand OneMusicBand : musicBands) {
                ObjectValidation objectValidation = new ObjectValidation();
                if (!objectValidation.checkObject(OneMusicBand)) {
                    return null;
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return musicBands;
    }

    /**
     * Метод для записи коллекции в файл.
     *
     * @param fileName   имя файла.
     * @param musicBands коллекция.
     * @throws IOException в случае возникновения ошибок записи в файл.
     */
    public void writeCollection(String fileName, HashSet<MusicBand> musicBands) throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        byte[] buffer = ("id,bandName,x,y,creationDate,numberOfParticipants,albumsCount,description,genre,personName," +
                "height,eyeColor,hairColor,nationality,personX,personY,personZ\n").getBytes();
        fileOutputStream.write(buffer, 0, buffer.length);
        for (MusicBand i : musicBands) {
            buffer = (i.getStringToSaveInFile() + "\n").getBytes();
            fileOutputStream.write(buffer, 0, buffer.length);
        }

    }
}
