package clientApp;

import IOutils.UserInputManager;
import commands.commandsUtils.ArgObjectForClient;
import commands.commandsUtils.ClientCommandsManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import messageUtils.Request;
import messageUtils.Response;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

import static clientApp.App.out;

@RequiredArgsConstructor
public class Client {
    private final String host;
    private final int port;
    private final UserInputManager userInputManager;
    private final ClientCommandsManager clientCommandsManager;
    private SocketChannel socketChannel;
    @Getter
    private ObjectOutputStream writer;
    @Getter
    private ObjectInputStream reader;
    private final int maxCountOfConnection = 10;
    private int countOfConnections = 0;

    public void run() throws IOException, InterruptedException {

        boolean statusOfRequest = true;
        boolean statusOfConnection;
        while (statusOfRequest && countOfConnections <= maxCountOfConnection) {
            try {
                statusOfConnection = connectToServer();
                if (statusOfConnection) statusOfRequest = requestToServer(userInputManager);
            } catch (IOException | IllegalArgumentException e) {
                out.println(e.getMessage());
            }
        }
        disconnect();
        out.println("The client has completed his work.");
    }

    private boolean connectToServer() throws IOException, InterruptedException {
        disconnect();
        countOfConnections++;
        try {
            out.println("An attempt to establish a connection...");
            socketChannel = SocketChannel.open(new InetSocketAddress(host, port));
            socketChannel.configureBlocking(true);
            out.println("The connection is established.");
            out.println("Waiting for a response from the server to a data exchange request.");
            writer = new ObjectOutputStream(socketChannel.socket().getOutputStream());
            reader = new ObjectInputStream(socketChannel.socket().getInputStream());
            out.println("Permission to exchange data has been received.");
            countOfConnections = 0;
            return true;
        } catch (IOException e) {
            out.println("An error occurred while connecting to the server.");
            Thread.sleep(5000);
            return false;
        } catch (IllegalArgumentException e) {
            out.println("The host or port is entered incorrectly.");
            return false;
        }
    }

    private void disconnect() throws IOException {
        if (socketChannel != null) socketChannel.close();
    }

    public boolean requestToServer(UserInputManager userInputManager) {
        Request request = null;
        Response response;
        do {
            try {
                request = userInputManager.input();
                if (request == null) return false;
                if (request.isEmpty()) {
                    request = null;

                } else {
                    if (request.getCommand().isServer()) {
                        writer.writeObject(request);
                        response = (Response) reader.readObject();
                        out.println(response.getResponseBody());
                    } else {
                        ArgObjectForClient argObject = new ArgObjectForClient(clientCommandsManager, request.getArgsOfCommand(), null);
                        out.println(request.getCommand().execute(argObject).getResult());
                    }
                }
            } catch (IllegalArgumentException e) {
                out.println(e.getMessage());
            } catch (IOException e) {
                out.println("The connection to the server is broken.");
                return false;
            } catch (ClassNotFoundException e) {
                out.println("An error occurred while reading the data.");
                return false;
            }
        } while (request == null || !request.getNameOfCommand().equals("EXIT"));
        return false;

    }

}
