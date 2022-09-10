package serverApp;

import dataBaseUtils.DataBaseControl;
import collectionUtil.CollectionManager;
import commands.commandsUtils.CommandResult;
import handlers.RequestHandler;
import handlers.ResponseHandler;
import messageUtils.Request;
import messageUtils.Response;
import messageUtils.ResponseCode;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.logging.Level;

import static serverApp.App.logger;


public class Server implements Runnable {
    private final CollectionManager collectionManager;
    private final ServerSocket serverSocket;
    private final DataBaseControl dataBaseControl;
    private final Executor reader = Executors.newCachedThreadPool();
    private final ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

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
                        logger.log(Level.SEVERE, "Problems with database access.");
                    }
                });
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Client was disconnected.");
        }
    }

    private void handleUser(Socket socket) throws SQLException {
        try {
            CommandResult commandResult;
            Request request;
            do {
                request = forkJoinPool.invoke(new RequestHandler(socket));
                commandResult = checkAuthorization(request, socket);
            } while (commandResult.getResponseCode() != ResponseCode.OK);
            processRequest(socket);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Some problems with connection. ");
        } catch (NoSuchAlgorithmException e) {
            logger.log(Level.SEVERE, "Some problems with access to DataBase.");
        } catch (RuntimeException e) {
            logger.log(Level.INFO, e.getMessage());
        }
    }

    private void sendResponse(Response response, Socket socket) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(response);
        byteArrayOutputStream.writeTo(socket.getOutputStream());
    }

    private void processRequest (Socket socket) throws IOException {
        while (true) {
            Request commandRequest = forkJoinPool.invoke(new RequestHandler(socket));
            Response response = forkJoinPool.invoke(new ResponseHandler(collectionManager, commandRequest));
            sendResponse(response, socket);
            if (commandRequest.getCommand().getName().equals("EXIT")) {
                break;
            }
        }
    }

    private boolean isIdenticalLogins (Request request) throws SQLException, NoSuchAlgorithmException {
        return dataBaseControl.checkUser(request.getAccount()).getResponseCode() == ResponseCode.OK ||
                dataBaseControl.checkUser(request.getAccount()).getResponseCode() == ResponseCode.WRONG_PASSWORD;
    }

    private CommandResult checkAuthorization (Request request, Socket socket)
            throws SQLException, NoSuchAlgorithmException, IOException {
        if (request.getCommand() == null) return checkRegistration(request, socket);
        return null;
    }

    private CommandResult checkRegistration(Request request, Socket socket)
            throws SQLException, NoSuchAlgorithmException, IOException {
        Response response;
        CommandResult commandResult;
        if (request.getAccount().isRegistered()) {
            commandResult = dataBaseControl.checkUser(request.getAccount());
        } else commandResult = checkLogin(request);
        response = new Response(commandResult.getResponseCode(), commandResult.getResult());
        sendResponse(response, socket);
        return commandResult;
    }

    private CommandResult checkLogin(Request request) throws SQLException, NoSuchAlgorithmException {
        CommandResult commandResult;
        if (isIdenticalLogins(request)) commandResult = new CommandResult("This login is already occupied.", ResponseCode.WRONG_LOGIN);
        else commandResult = dataBaseControl.addUser(request.getAccount());
        return commandResult;
    }
}
