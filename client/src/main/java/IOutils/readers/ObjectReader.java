package IOutils.readers;

import IOutils.InputAndOutput;
import data.MusicBand;
import data.MusicGenre;
import collectionUtils.MusicBandBuilder;

import java.time.ZonedDateTime;
import java.util.Scanner;

/**
 * A class for creating object fields reading from the console or script.
 */
public class ObjectReader {
    private final Scanner scanner;
    private final boolean showMessages;
    private CoordinatesReader coordinatesReader;
    private PersonReader personReader;
    private InputAndOutput inputAndOutput;

    public ObjectReader(Scanner scanner, boolean showMessages) {
        this.scanner = scanner;
        this.showMessages = showMessages;
        this.inputAndOutput = new InputAndOutput(scanner, showMessages);
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
        while (true) {
            String name = inputAndOutput.readLine("Input name of MusicBand: ");
            if (name == null || name.equals("")) {
                inputAndOutput.printLine("Wrong format of input! Name can't be an empty line.");
            } else return name;
            if (!showMessages) throw new NumberFormatException("Wrong format of music band's name.");
        }
    }

    public Long readNumberOfParticipants() {
        while (true) {
            try {
                Long numberOfParticipants = Long.parseLong(inputAndOutput.readLine("Input numberOfParticipants:"));
                if (numberOfParticipants <= 0) {
                    inputAndOutput.printLine("Wrong format of input! Number of participants can't be null and should be more than 0.");
                } else return numberOfParticipants;
            } catch (NumberFormatException e) {
                inputAndOutput.printLine("Wrong format of input! It should be a long number.");
            }
            if (!showMessages) throw new NumberFormatException("Wrong format of participants");
        }
    }

    public long readAlbumsCount() {
        while (true) {
            try {
                long albumsCount = Long.parseLong(inputAndOutput.readLine("Input albumsCount: "));
                if (albumsCount > 0) return albumsCount;
                else inputAndOutput.printLine("Wrong format of input! AlbumsCount must be more than 0.");
            } catch (NumberFormatException e) {
                inputAndOutput.printLine("Wrong format of input! It must be a long number more than 0.");
            }
            if (!showMessages) throw new NumberFormatException("Wrong format of albums count.");
        }
    }

    public String readDescription() {
        while (true) {
            String description = inputAndOutput.readLine("Input description: ");
            if (description.equals("")) {
                inputAndOutput.printLine("Wrong format of input! Description can't be a null.");
            } else return description;
            if (!showMessages) throw new NumberFormatException("Wrong format of description.");
        }
    }

    public MusicGenre readGenre() {
        while (true) {
            try {
                return MusicGenre.valueOf(inputAndOutput.readLine("Input music genre(JAZZ, POST_ROCK, PUNK_ROCK)").toUpperCase());
            } catch (IllegalArgumentException e) {
                inputAndOutput.printLine("Wrong format of input! Choose a genre from the suggested list!");
            }
            if (!showMessages) throw new NumberFormatException("Wrong format of genre.");
        }
    }

}
