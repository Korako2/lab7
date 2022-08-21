package serverApp;

import DataBaseUtils.DataBaseControl;
import collectionUtil.CollectionManager;
import commands.commandsUtils.ArgObjectForServer;
import commands.commandsUtils.CommandResult;
import messageUtils.Request;
import messageUtils.Response;
import messageUtils.ResponseCode;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

import static serverApp.App.logger;


public class Server implements Runnable {
    private final CollectionManager collectionManager;
    private final ServerSocket serverSocket;
    private final DataBaseControl dataBaseControl;
    private final ExecutorService reader = Executors.newFixedThreadPool(4);

    public Server(int port, CollectionManager collectionManager, DataBaseControl dataBaseControl) throws IOException {
        this.collectionManager = collectionManager;
        this.serverSocket = new ServerSocket(port);
        this.dataBaseControl = dataBaseControl;
    }

    public void run() {
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                reader.execute(() -> {
                    try {
                        handleUser(socket);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Client was disconnected.");
            //saveIfExit();
        }
    }

    private void handleUser(Socket socket) throws SQLException {
        try {
            CommandResult commandResult = null;
            Request request;
            do {
                request = receiveRequest(socket);
                if (request.getCommand() == null) {
                    Response response;
                    if (request.getAccount().isRegistered()) {
                        commandResult = dataBaseControl.checkUser(request.getAccount());
                    } else {
                        commandResult = dataBaseControl.addUser(request.getAccount());
                    }
                    response = new Response(commandResult.getResponseCode(), commandResult.getResult());
                    sendResponse(response, socket);
                }
            } while (commandResult.getResponseCode() != ResponseCode.OK);
            while (true) {
                //dataBaseControl.addToDataBase(request.getMusicBand(), request.getAccount().getUserName()); //todo
                Request commandRequest = receiveRequest(socket);
                Response response = createResponse(commandRequest);
                sendResponse(response, socket);
                if (commandRequest.getCommand().getName().equals("EXIT")) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            //logger.log(Level.SEVERE, "Some problems with connection. ");
        }
    }


    private Request receiveRequest(Socket socket) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        return (Request) objectInputStream.readObject();
    }

    private Response createResponse(Request request) {
        ArgObjectForServer argObject = new ArgObjectForServer(collectionManager, request.getArgsOfCommand(),
                request.getMusicBand(), request.getAccount().getUserName());
        CommandResult commandResult = null;
        commandResult = request.getCommand().execute(argObject);
        return new Response(commandResult.getResponseCode(), commandResult.getResult());
    }


    private void sendResponse(Response response, Socket socket) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(response);
        byteArrayOutputStream.writeTo(socket.getOutputStream());
    }

    /*private void saveIfExit() {
        try {
            collectionManager.saveCollection();
            logger.log(Level.INFO, "The collection was saved");
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "This file wasn't found");
        } catch (SecurityException e) {
            logger.log(Level.SEVERE, "Write access to the file is denied");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Some I/O errors occur: ");
        }
    }*/

}

