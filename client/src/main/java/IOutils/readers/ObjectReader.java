package IOutils.readers;

import IOutils.InputAndOutput;
import data.MusicBand;
import data.MusicGenre;
import collectionUtils.MusicBandBuilder;

import java.time.ZonedDateTime;
import java.util.Scanner;
import java.util.function.Function;

/**
 * A class for creating object fields reading from the console or script.
 */
public class ObjectReader {
    private final CoordinatesReader coordinatesReader;
    private final PersonReader personReader;
    private final InputAndOutput inputAndOutput;

    public ObjectReader(Scanner scanner, boolean consoleReading) {
        this.inputAndOutput = new InputAndOutput(scanner, consoleReading);
        this.coordinatesReader = new CoordinatesReader(inputAndOutput);
        this.personReader = new PersonReader(inputAndOutput);
    }

    /**
     * A method for creating an object based on the read fields.
     *
     * @return a collection element of the type {@link MusicBand}.
     */
    public MusicBand readObject() {
        MusicBandBuilder musicBandBuilder = new MusicBandBuilder();
        return musicBandBuilder.setName(readNameOfMusicBand())
                .setAlbumsCount(readAlbumsCount())
                .setCoordinates(coordinatesReader.readCoordinates())
                .setCreationDate(ZonedDateTime.now())
                .setDescription(readDescription())
                .setGenre(readGenre())
                .setFrontMan(personReader.readPerson())
                .setNumberOfParticipants(readNumberOfParticipants()).build();
    }


    public String readNameOfMusicBand() {
        Function<String, String> mapper = inputAndOutput::readLine;
        Function<String, Boolean> condition = n -> (n != null && !n.equals(""));
        return inputAndOutput.readLineAs("Input name of MusicBand. Name can't be an empty line: ", mapper, condition);
    }

    public Long readNumberOfParticipants() {
        Function<String, Long> mapper = n -> Long.parseLong(inputAndOutput.readLine(n));
        Function<Long, Boolean> condition = n -> (n != null && n >= 0);
        return inputAndOutput.readLineAs("Input numberOfParticipants. " +
                "Number of participants can't be null and should be more than 0:  ", mapper, condition);
    }

    public long readAlbumsCount() {
        Function<String, Long> mapper = n -> Long.parseLong(inputAndOutput.readLine(n));
        Function<Long, Boolean> condition = n -> (n > 0);
        return inputAndOutput.readLineAs("Input albumsCount. " +
                "It must be a long number more than 0: ", mapper, condition);
    }

    public String readDescription() {
        Function<String, String> mapper = inputAndOutput::readLine;
        Function<String, Boolean> condition = n -> (!n.equals(""));
        return inputAndOutput.readLineAs("Input description. Description can't be a null: ", mapper, condition);
    }

    public MusicGenre readGenre() {
        Function<String, MusicGenre> mapper = n -> MusicGenre.valueOf(inputAndOutput.readLine(n).toUpperCase());
        Function<MusicGenre, Boolean> condition = n -> (true);
        return inputAndOutput.readLineAs("Input music genre(JAZZ, POST_ROCK, PUNK_ROCK): ", mapper, condition);
    }

}
