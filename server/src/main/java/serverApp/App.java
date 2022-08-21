package serverApp;

import DataBaseUtils.DataBaseControl;
import collectionUtil.CollectionManager;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    public static Logger logger = Logger.getLogger(App.class.getName());
    private static final int port = 4444;

    public static void main(String[] args) {
        if (args.length != 5) {
            logger.log(Level.SEVERE, "Invalid argument input format: <host>, <port>, <database>, <user>, <password>");
            System.exit(-1);
        }
        Map env = System.getenv();
        String fileName = (String) env.get("FILE_NAME");
        if (fileName == null) {
            logger.log(Level.SEVERE, "No file name in environment variable FILE_NAME");
            System.exit(-1);
        }
        Pattern pattern = Pattern.compile("/*/dev/*");
        File file = new File(fileName);
        Matcher matcher = pattern.matcher(file.getAbsolutePath());
        if (matcher.find()) {
            logger.log(Level.SEVERE, "Incorrect file name or data in file.");
            System.exit(-1);
        }
        /*try {
            if (!collectionManager.fillCollection(fileName)) {
                logger.log(Level.SEVERE, "Incorrect file name or data in file.");
                System.exit(-1);
            }
        } catch (IOException | ParseException | NumberFormatException e) {
            logger.log(Level.SEVERE, e.getMessage());
            System.exit(-1);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Some exception: " + e.getMessage());
            System.exit(-1);
        }*/
        try {
            DataBaseControl dataBaseControl = new DataBaseControl(args);
            CollectionManager collectionManager = new CollectionManager(dataBaseControl);
            dataBaseControl.getAllFromDB(collectionManager);
            Server server = new Server(port, collectionManager, dataBaseControl);
            //System.out.println(collectionManager.getMusicBands());
            Thread thread = new Thread(server);
            thread.start();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error connecting to the database.");
            e.printStackTrace();
            System.exit(-1);
        }
        catch (IOException e) {
            logger.log(Level.SEVERE, "I/O error occurs when opening the socket.");
        }
    }
}
