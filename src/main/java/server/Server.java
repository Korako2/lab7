package server;

import lombok.RequiredArgsConstructor;
import server.collectionUtil.CollectionManager;
import server.commands.ArgObjectForServer;
import sharedClasses.messageUtils.Request;
import sharedClasses.messageUtils.Response;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;

import static server.App.LOGGER;


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
            throw new IOException("Не удалось открыть ServerSocket");
        }
    }
    private void saveIfExit() throws IOException {
        try {
            collectionManager.saveCollection();
            exit();
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE,"This file wasn't found");
        } catch (SecurityException e) {
            LOGGER.log(Level.SEVERE,"Write access to the file is denied");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE,"Some I/O errors occur");
        }
        LOGGER.log(Level.INFO, "The collection was saved");
        run();
    }
    private void exit() throws IOException {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
            LOGGER.log(Level.INFO,"Соединение с клиентом разорвано.");

        } catch (IOException e) {
            LOGGER.log(Level.INFO,"Ошибка при завершении соединения с клиентом.");
        }
    }

    private Socket connectToClient() throws IOException {
        Socket clientSocket = serverSocket.accept();
        LOGGER.log(Level.INFO,"Соединение с клиентом успешно установлено.");
        return clientSocket;
    }

    private boolean processClientRequest(Socket clientSocket) throws IOException, ClassNotFoundException {
        Request request = null;
        Response response = null;
        try {
            ObjectInputStream clientReader = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream clientWriter = new ObjectOutputStream(clientSocket.getOutputStream());
            do {
                request = (Request) clientReader.readObject();
                ArgObjectForServer argObject = new ArgObjectForServer(collectionManager, request.getArgsOfCommand(), request.getMusicBand());
                response = new Response("OK", request.getCommand().execute(argObject)); //todo
                clientWriter.writeObject(response);
                if (request.getCommand().getName().toUpperCase().equals("EXIT")) {
                    return false;
                }
                clientWriter.flush();
            } while (true);
        } catch (IOException e) {
            throw new IOException("Разрыв соединения с клиентом.");
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("Произошла ошибка при чтении полученных данных!");
        }
    }
}
