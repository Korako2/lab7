package collectionUtil;

import data.Coordinates;
import data.Location;
import data.MusicBand;
import data.Person;

/**
 * A class for validating new collection items.
 */
public class ObjectValidation {
    /**
     * Method for checking object fields {@link MusicBand}.
     *
     * @param musicBand collection object {@link MusicBand}.
     * @return true if the object fields are valid, otherwise false.
     */
    public boolean checkObject(MusicBand musicBand) {
        return (musicBand.getId() > 0 && musicBand.getName() != null && musicBand.getCoordinates() != null &&
                musicBand.getCreationDate() != null &&
                musicBand.getAlbumsCount() > 0 && musicBand.getDescription() != null &&
                musicBand.getGenre() != null && musicBand.getFrontMan() != null &&
                checkCoordinates(musicBand.getCoordinates()) && checkPerson(musicBand.getFrontMan()));
    }

    /**
     * Method for checking object fields {@link Coordinates}.
     *
     * @param coordinates new object {@link Coordinates}.
     * @return true if the object fields are valid, otherwise false.
     */
    private boolean checkCoordinates(Coordinates coordinates) {
        return coordinates.getX() <= 146 && coordinates.getX() != null;
    }

    /**
     * Method for checking object fields {@link Person}.
     *
     * @param person new object {@link Person}.
     * @return true if the object fields are valid, otherwise false.
     */
    private boolean checkPerson(Person person) {
        return (person.getName() != null &&
                person.getHairColor() != null && person.getNationality() != null && checkLocation(person.getLocation()));
    }

    /**
     * Method for checking object fields {@link Location}.
     *
     * @param location new object {@link Location}.
     * @return true if the object fields are valid, otherwise false.
     */
    private boolean checkLocation(Location location) {
        return location.getX() != null && location.getZ() != null;
    }
}
