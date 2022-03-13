package IOutils;

import collection.*;

import java.time.ZonedDateTime;
import java.util.Scanner;

public class ObjectReader {
    private final Scanner scanner;
    private final boolean showMessages;

    public ObjectReader(Scanner scanner, boolean showMessages) {
        this.scanner = scanner;
        this.showMessages = showMessages;
    }

    public MusicBand readObject() {
        return new MusicBand(readNameOfMusicBand(), readCoordinates(), ZonedDateTime.now(),
                readNumberOfParticipants(), readAlbumsCount(), readDescription(), readGenre(), readPerson());
    }

    public String readNameOfMusicBand() {
        while (true) {
            String name = readLine("Input name of MusicBand: ");
            if (name == null || name.equals("")) {
                printLine("Wrong format of input! Name can't be an empty line.");
            } else return name;
            if (!showMessages) throw new NumberFormatException("Wrong format of music band's name.");
        }
    }

    public Long readNumberOfParticipants() {
        while (true) {
            try {
                Long numberOfParticipants = Long.parseLong(readLine("Input numberOfParticipants:"));
                if (numberOfParticipants <= 0) {
                    printLine("Wrong format of input! Number of participants can't be null and should be more than 0.");
                } else return numberOfParticipants;
            } catch (NumberFormatException e) {
                printLine("Wrong format of input! It should be a long number.");
            }
            if (!showMessages) throw new NumberFormatException("Wrong format of participants");
        }
    }

    public Coordinates readCoordinates() {
        Float x = readXCoordinate();
        int y = readYCoordinate();
        return new Coordinates(x, y);
    }

    public long readAlbumsCount() {
        while (true) {
            try {
                long albumsCount = Long.parseLong(readLine("Input albumsCount: "));
                if (albumsCount > 0) return albumsCount;
                else printLine("Wrong format of input! AlbumsCount must be more than 0.");
            } catch (NumberFormatException e) {
                printLine("Wrong format of input! It must be a long number more than 0.");
            }
            if (!showMessages) throw new NumberFormatException("Wrong format of albums count.");
        }
    }

    public String readDescription() {
        while (true) {
            String description = readLine("Input description: ");
            if (description.equals("")) {
                printLine("Wrong format of input! Description can't be a null.");
            } else return description;
            if (!showMessages) throw new NumberFormatException("Wrong format of description.");
        }
    }

    public MusicGenre readGenre() {
        while (true) {
            try {
                return MusicGenre.valueOf(readLine("Input music genre(JAZZ, POST_ROCK, PUNK_ROCK)").toUpperCase());
            } catch (IllegalArgumentException e) {
                printLine("Wrong format of input! Choose a genre from the suggested list!");
            }
            if (!showMessages) throw new NumberFormatException("Wrong format of genre.");
        }
    }

    public Person readPerson() {
        String name = readNameOfPerson();
        Double height = readHeight();
        EyeColor eyeColor = readEyeColor();
        HairColor hairColor = readHairColor();
        Country nationality = readNationality();
        Location location = readLocation();
        return new Person(name, height, eyeColor, hairColor, nationality, location);
    }

    public Country readNationality() {
        while (true) {
            try {
                return Country.valueOf(readLine("Input country(GERMANY, INDIA, VATICAN):").toUpperCase());
            } catch (IllegalArgumentException e) {
                printLine("Wrong format of input! Choose a country from the suggested list!");
            }
            if (!showMessages) throw new NumberFormatException("Wrong format national city.");
        }
    }

    public String readNameOfPerson() {
        while (true) {
            String name = readLine("Input name of person: ");
            if (name.equals("")) {
                printLine("Wrong format of input! Name can't be a null.");
            } else return name;
            if (!showMessages) throw new NumberFormatException("Wrong format of name.");
        }
    }

    public HairColor readHairColor() {
        while (true) {
            try {
                return HairColor.valueOf(readLine("Input hair color(RED, BLACK, BLUE, ORANGE, WHITE):").toUpperCase());
            } catch (IllegalArgumentException e) {
                printLine("Wrong format of input! Choose a hair color from the suggested list!");
            }
            if (!showMessages) throw new NumberFormatException("Wrong format of hair color.");
        }
    }

    public EyeColor readEyeColor() {
        while (true) {
            try {
                String line = readLine("Input eye color(RED, WHITE, BROWN):").toUpperCase();
                if (line.equals("")) return null;
                else {
                    return EyeColor.valueOf(line);
                }
            } catch (IllegalArgumentException e) {
                printLine("Wrong format of input! Choose an eye color from the suggested list!");
            }
            if (!showMessages) throw new NumberFormatException("Wrong format of eye color.");
        }
    }

    public Double readHeight() {
        while (true) {
            try {
                String line = readLine("Input height:");
                if (line.equals("")) return null;
                else {
                    Double height = Double.parseDouble(line.replace(",", "."));
                    if (height > 0) return height;
                    else {
                        printLine("Height must be more than 0.");
                    }
                }
            } catch (NumberFormatException e) {
                printLine("Wrong format of input! Height must be a number!");
            }
            if (!showMessages) throw new NumberFormatException("Wrong format of height.");
        }
    }

    public Location readLocation() {
        Float x = readXCoordinateOfLocation();
        int y = readYCoordinateOfLocation();
        Long z = readZCoordinateOfLocation();
        return new Location(x, y, z);
    }

    public Float readXCoordinateOfLocation() {
        while (true) {
            try {
                return Float.parseFloat(readLine("Input x coordinate of location:").replace(",", "."));
            } catch (NumberFormatException e) {
                printLine("Wrong format of input! It should be a float number.");
            }
            if (!showMessages) throw new NumberFormatException("Wrong format of X coordinate of location.");
        }
    }

    public int readYCoordinateOfLocation() {
        while (true) {
            try {
                return Integer.parseInt(readLine("Input y coordinate of location:"));
            } catch (NumberFormatException e) {
                printLine("Wrong format of input! It should be a float number.");
            }
            if (!showMessages) throw new NumberFormatException("Wrong format of Y coordinate of location.");
        }
    }

    public Long readZCoordinateOfLocation() {
        while (true) {
            try {
                return Long.parseLong(readLine("Input z coordinate of location:"));
            } catch (NumberFormatException e) {
                printLine("Wrong format of input! It should be a Long number.");
            }
            if (!showMessages) throw new NumberFormatException("Wrong format of Z coordinate of location.");
        }
    }

    public Float readXCoordinate() {
        while (true) {
            try {
                Float x = Float.parseFloat(readLine("Input x coordinate. Max field value: 146.").replace(",", "."));
                if (x > 146) {
                    printLine("Wrong format of input! Max field value: 146. ");
                } else return x;
            } catch (NumberFormatException e) {
                printLine("Wrong format of input! It should be a float number.");
            }
            if (!showMessages) throw new NumberFormatException("Wrong format of X coordinate.");
        }
    }

    public int readYCoordinate() {
        while (true) {
            try {
                return Integer.parseInt(readLine("Input y coordinate. It must be integer."));
            } catch (NumberFormatException e) {
                printLine("Wrong format of input! It must be integer.");
            }
            if (!showMessages) throw new NumberFormatException("Wrong format of Y coordinate.");
        }

    }

    public String readLine(String message) {
        if (showMessages) System.out.println(message);
        return scanner.nextLine();
    }

    public void printLine(String message) {
        if (showMessages) System.out.println(message);
    }
}
