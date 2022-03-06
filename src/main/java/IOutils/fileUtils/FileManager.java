package IOutils.fileUtils;

import collection.MusicBand;
import collection.collectionUtil.ObjectValidation;

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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return musicBands;
    }

    public void writeCollection() {

    }
}
