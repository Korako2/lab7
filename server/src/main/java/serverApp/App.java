package serverApp;

import dataBaseUtils.DataBaseControl;
import collectionUtil.CollectionManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
    public static Logger logger = Logger.getLogger(App.class.getName());
    private static final int port = 4444;

    public static void main(String[] args) {
        if (args.length != 5) {
            logger.log(Level.SEVERE, "Invalid argument input format: <host>, <port>, <database>, <user>, <password>");
            System.exit(-1);
        }
        try {
            DataBaseControl dataBaseControl = new DataBaseControl(args);
            CollectionManager collectionManager = new CollectionManager(dataBaseControl);
            dataBaseControl.getAllFromDB(collectionManager);
            Server server = new Server(port, collectionManager, dataBaseControl);
            Thread thread = new Thread(server);
            thread.start();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error connecting to the database.");
            e.printStackTrace();
            System.exit(-1);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "I/O error occurs when opening the socket.");
        }
    }
}
