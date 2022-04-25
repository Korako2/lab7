package server;

import server.collectionUtil.CollectionManager;
import server.commands.ServerCommandsManager;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) {
        ServerCommandsManager serverCommandsManager = new ServerCommandsManager();
        CollectionManager collectionManager = new CollectionManager();
        Map env = System.getenv();
        String fileName = (String) env.get("FILE_NAME");
        if (fileName == null) {
            //System.out.println("Please, save the file name in environment variable FILE_NAME");
            System.exit(-1);
        }
        Pattern pattern = Pattern.compile("/*/dev/*");
        File file = new File(fileName);
        Matcher matcher = pattern.matcher(file.getAbsolutePath());
        if (matcher.find()) {
            //System.out.println("Incorrect file name or data in file.");
            System.exit(-1);
        }
        try {
            if (!collectionManager.fillCollection(fileName)) {
         //       System.out.println("Incorrect file name or data in file.");
                System.exit(-1);
            }
        } catch (IOException | ParseException | NumberFormatException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        } catch (Exception e) {
            System.out.println("Some exception!");
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        Server server = new Server(4444, collectionManager, serverCommandsManager);
        try {
            server.run();
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
