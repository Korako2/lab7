package IOutils.fileUtils;

import collection.MusicBand;
import collection.collectionUtil.ObjectValidation;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;

public class FileManager {
    CSVParser csvParser = new CSVParser();

    public HashSet<MusicBand> readCollection(String file) {
        HashSet<MusicBand> musicBands = null;
        try {
            musicBands = csvParser.parse(file);
            for (MusicBand OneMusicBand : musicBands) {
                ObjectValidation objectValidation = new ObjectValidation();
                objectValidation.checkObject(OneMusicBand);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return musicBands;
    }

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
