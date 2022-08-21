package DataBaseUtils;

import collectionUtil.CollectionManager;
import commands.commandsUtils.CommandResult;
import data.MusicBand;
import lombok.SneakyThrows;
import messageUtils.ResponseCode;
import security.Account;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Random;
import java.util.logging.Level;

import static serverApp.App.logger;

public class DataBaseControl {
    private final String URL;
    private final String USER;
    private final String PASS;
    private Connection connection;
    private Statement statement;

    public DataBaseControl(String[] args) {
        String host = args[0];
        String port = args[1];
        String nameOfTheDataBase =  args[2];
        URL = "jdbc:postgresql://" + host + ":" + port + "/" + nameOfTheDataBase;
        USER = args[3];
        PASS = args[4];
    }

    private void setConnection() throws SQLException {
        connection = DriverManager.getConnection(URL, USER, PASS);
    }
    public void getAllFromDB(CollectionManager collectionManager) throws SQLException{
        setConnection();
        logger.log(Level.INFO, "The connection to the database is established.");
        createSequence();
        createUserTable();
        createMusicBand();
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM music_band");
        ObjectFromDataBaseReader objectFromDataBaseReader = new ObjectFromDataBaseReader(resultSet);
        while (resultSet.next()) {
            MusicBand musicBand = objectFromDataBaseReader.readObject();
            collectionManager.fillCollection(musicBand);
        }
        statement.close();
    }

    private void createSequence() throws SQLException {
        statement = connection.createStatement();
        String request = "CREATE SEQUENCE IF NOT EXISTS id_generation START 1";
        statement.executeUpdate(request);
        statement.close();
    }

    private void createUserTable() throws SQLException {
        statement = connection.createStatement();
        String request = "CREATE TABLE IF NOT EXISTS users (login VARCHAR(255) PRIMARY KEY, password VARCHAR(255), salt VARCHAR(255))";
        statement.executeUpdate(request);
        statement.close();
    }

    private void createMusicBand() throws SQLException {
        statement = connection.createStatement();
        String request = "CREATE TABLE IF NOT EXISTS music_band (\n" +
                "id BIGINT PRIMARY KEY CHECK(id > 0) DEFAULT nextval('id_generation'), \n" +
                "band_name VARCHAR(255) NOT NULL, \n" + //todo
                "x REAL NOT NULL CHECK(x <=146), \n" +
                "y INTEGER, \n" +
                "creation_date VARCHAR(255) NOT NULL, \n" +
                "number_of_participants BIGINT NOT NULL CHECK (number_of_participants > 0), \n" +
                "albums_count BIGINT CHECK(albums_count > 0), \n" +
                "description VARCHAR(255) NOT NULL, \n" +
                "genre VARCHAR(255), \n" +
                "person_name VARCHAR(255) NOT NULL, \n" +
                "height DOUBLE PRECISION CHECK(height >0), \n" +
                "eye_color VARCHAR(255) NOT NULL, \n" +
                "hair_color VARCHAR(255) NOT NULL, \n" +
                "nationality VARCHAR(255) NOT NULL, \n" +
                "person_x REAL NOT NULL, \n" +
                "person_y INTEGER NOT NULL, \n" +
                "person_z BIGINT NOT NULL, \n" +
                "owner VARCHAR(255) NOT NULL);";
        statement.executeUpdate(request);
        statement.close();
    }

    public MusicBand addToDataBase(MusicBand musicBand, String userName) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO music_band (band_name, x, y," +
                "creation_date, number_of_participants, albums_count, description, genre, person_name, height, eye_color," +
                "hair_color, nationality, person_x, person_y, person_z, owner)" +
                " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, musicBand.getName());
        preparedStatement.setFloat(2, musicBand.getCoordinates().getX());
        preparedStatement.setInt(3, musicBand.getCoordinates().getY());
        preparedStatement.setString(4, musicBand.getStringDate());
        preparedStatement.setLong(5, musicBand.getNumberOfParticipants());
        preparedStatement.setLong(6, musicBand.getAlbumsCount());
        preparedStatement.setString(7, musicBand.getDescription());
        preparedStatement.setString(8, musicBand.getGenre().getMusic());
        preparedStatement.setString(9, musicBand.getFrontMan().getName());
        preparedStatement.setDouble(10, musicBand.getFrontMan().getHeight());
        preparedStatement.setString(11, musicBand.getFrontMan().getEyeColor().getColor());
        preparedStatement.setString(12, musicBand.getFrontMan().getHairColor().getColor());
        preparedStatement.setString(13, musicBand.getFrontMan().getNationality().getCountry());
        preparedStatement.setFloat(14, musicBand.getFrontMan().getLocation().getX());
        preparedStatement.setInt(15, musicBand.getFrontMan().getLocation().getY());
        preparedStatement.setLong(16, musicBand.getFrontMan().getLocation().getZ()); //todo проверки на Null
        preparedStatement.setString(17, userName);
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                musicBand.setId(resultSet.getInt("id"));
            }
        }
        preparedStatement.close();
        return musicBand;
    }
    public boolean removeById(long id, String userName) throws SQLException {
        boolean flag = false;
        PreparedStatement statement = connection.prepareStatement("DELETE FROM music_band WHERE id = ? AND owner = ?");
        statement.setLong(1, id);
        statement.setString(2, userName);
        int count = statement.executeUpdate();
        if (count > 0) flag = true;
        return flag;
    }

    @SneakyThrows
    public CommandResult addUser(Account account) throws SQLException {
        String result = "New user added";
        ResponseCode responseCode = ResponseCode.OK;
        if (checkUser(account).getResponseCode() == ResponseCode.WRONG_PASSWORD) {
            return new CommandResult("Such a user already exists", ResponseCode.WRONG_LOGIN);
        }
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users VALUES (?,?,?);");
        preparedStatement.setString(1, account.getUserName());
        String salt = generateSalt();
        preparedStatement.setString(2, hash(account.getPassword(), salt));
        preparedStatement.setString(3, salt);
        int count = preparedStatement.executeUpdate(); //todo Если логины совпадают то ошибка
        if (count <= 0) {
            result = "The new user has not been added, try to come up with a different login";
            responseCode = ResponseCode.WRONG_LOGIN;
        }
        return new CommandResult(result, responseCode);
    }

    private String hash(String password, String salt) throws NoSuchAlgorithmException {
        String pepper = "saf24A<23.";
        String hash = pepper + password + salt;
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
        byte[] encoded = messageDigest.digest(hash.getBytes(StandardCharsets.UTF_8));
        BigInteger bigInteger = new BigInteger(1, encoded);
        return bigInteger.toString(16);
    }

    private String generateSalt() {
        Random random = new Random();
        byte[] bytes = new byte[10];
        random.nextBytes(bytes);
        BigInteger bi = new BigInteger(1, bytes);
        return bi.toString(10);
    }
    @SneakyThrows //todo
    public CommandResult checkUser(Account account) {
        String result = "Wrong login";
        ResponseCode responseCode = ResponseCode.WRONG_LOGIN;
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT  password, salt FROM users WHERE login = ?");
        preparedStatement.setString(1, account.getUserName());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            if (resultSet.getString(1).equals(hash(account.getPassword(), resultSet.getString(2)))) {
                result = "Authorization is successful";
                responseCode = ResponseCode.OK;
            }
            else {
                result = "Invalid password";
                responseCode = ResponseCode.WRONG_PASSWORD;
            }
        }
        return new CommandResult(result, responseCode);
    }

}
