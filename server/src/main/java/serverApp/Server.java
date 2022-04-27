package serverApp;

import lombok.RequiredArgsConstructor;
import collectionUtil.CollectionManager;
import commands.commandsUtils.ArgObjectForServer;
import commands.commandsUtils.CommandResult;
import messageUtils.Request;
import messageUtils.Response;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import static serverApp.App.LOGGER;


@RequiredArgsConstructor
public class Server {
    private final int port;
    private final CollectionManager collectionManager;
    private ServerSocket serverSocket;


    public void run() throws IOException {
        boolean status = true;
        openServerSocket();
        while (status) {
            try {
                Socket clientSocket = connectToClient();
                status = processClientRequest(clientSocket);
            } catch (ClassNotFoundException e) {
                LOGGER.log(Level.SEVERE, e.getMessage());
                status = false;
            } catch (IOException e) {
                LOGGER.log(Level.WARNING, e.getMessage());
                status = false;
            }
        }
        saveIfExit();
    }

    private void openServerSocket() throws IOException {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new IOException("Failed to open ServerSocket.");
        }
    }

    private void saveIfExit() throws IOException {
        try {
            collectionManager.saveCollection();
            LOGGER.log(Level.INFO, "The collection was saved");
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "This file wasn't found");
        } catch (SecurityException e) {
            LOGGER.log(Level.SEVERE, "Write access to the file is denied");
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.log(Level.SEVERE, "Some I/O errors occur: " + e.getMessage());
        }
        exit();
        run();
    }

    private void exit() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
            LOGGER.log(Level.INFO, "The connection with the client closed.");

        } catch (IOException e) {
            LOGGER.log(Level.INFO, "Error when completing the connection with the client.");
        }
    }

    private Socket connectToClient() throws IOException {
        Socket clientSocket = serverSocket.accept();
        LOGGER.log(Level.INFO, "The connection with the client has been successfully established");
        return clientSocket;
    }

    private boolean processClientRequest(Socket clientSocket) throws IOException, ClassNotFoundException {
        Request request;
        Response response;
        try {
            ObjectInputStream clientReader = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream clientWriter = new ObjectOutputStream(clientSocket.getOutputStream());
            do {
                request = (Request) clientReader.readObject();
                ArgObjectForServer argObject = new ArgObjectForServer(collectionManager, request.getArgsOfCommand(), request.getMusicBand());
                CommandResult commandResult = request.getCommand().execute(argObject);
                response = new Response(commandResult.getResponseCode(), commandResult.getResult());
                clientWriter.writeObject(response);
                if (request.getCommand().getName().equals("EXIT")) {
                    return false;
                }
                clientWriter.flush();
            } while (true);
        } catch (IOException e) {
            throw new IOException("Disconnection from the client.");
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("An error occurred while reading the received data!");
        }
    }
}
