package clientApp;

import IOutils.AuthorizationManager;
import IOutils.UserInputManager;
import commands.commandsUtils.ArgObjectForClient;
import commands.commandsUtils.ClientCommandsManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import messageUtils.Request;
import messageUtils.Response;
import messageUtils.ResponseCode;
import security.Account;

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

    private final AuthorizationManager authorizationManager;
    private SocketChannel socketChannel;
    @Getter
    private ObjectOutputStream writer;
    private final int MAX_CONNECTION_COUNT = 100;
    private int countOfConnections = 0;

    private Account account;
    private boolean isWrongPassword = false;

    public void run() throws IOException, InterruptedException {

        boolean statusOfRequest = true;
        boolean statusOfConnection;
        boolean statusOfAuthorization = false;
        while (statusOfRequest && countOfConnections <= MAX_CONNECTION_COUNT) {
            try {
                statusOfConnection = connectToServer();
                while (!statusOfAuthorization) {
                    statusOfAuthorization = authorization();
                }
                if (statusOfConnection) {
                    statusOfRequest = requestToServer(userInputManager);
                }
            } catch (IOException | IllegalArgumentException | ClassNotFoundException e) {
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

    public boolean requestToServer(UserInputManager userInputManager) throws IOException {
        Request request = null;
        do {
            try {
                request = userInputManager.inputCommand(account);
                if (request == null) break;
                processRequest(request);
            } catch (IllegalArgumentException e) {
                out.println(e.getMessage());
            } catch (IOException e) {
                out.println("The connection to the server is broken.");
                break;
            } catch (ClassNotFoundException e) {
                out.println("An error occurred while reading the data.");
                break;
            }
        } while (requestHasNext(request));
        return request == null || !request.getNameOfCommand().equals("EXIT");
    }

    private boolean requestHasNext(Request request) {
        return request == null || !request.isEmpty() && !request.getNameOfCommand().equals("EXIT");
    }
    private void processRequest(Request request) throws IOException, ClassNotFoundException {
        if (!request.isEmpty()) {
            if (request.getCommand().isServer()) {
                sendRequest(request);
                receiveResponse();
            } else {
                executeCommandsOnClient(request);
            }
        }
    }
    private void sendRequest(Request request) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        writer = new ObjectOutputStream(byteArrayOutputStream);
        writer.writeObject(request);
        byteArrayOutputStream.writeTo(socketChannel.socket().getOutputStream());
    }

    private Response receiveResponse() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(socketChannel.socket().getInputStream());
        Response response = (Response) objectInputStream.readObject();
        out.println(response.getResponseBody());
        return response;
    }

    private void executeCommandsOnClient(Request request) {
        ArgObjectForClient argObject = new ArgObjectForClient(clientCommandsManager, request.getArgsOfCommand(), null);
        out.println(request.getCommand().execute(argObject).getResult());
    }

    private boolean authorization() throws IOException, ClassNotFoundException {
        account = authorizationManager.getAuthorizationData(isWrongPassword);
        Request request = new Request(null, null, null, account);
        sendRequest(request);
        Response response = receiveResponse();
        if (response.getResponseCode() == ResponseCode.WRONG_PASSWORD) isWrongPassword = true;
        if (response.getResponseCode() == ResponseCode.OK) return true;
        return false;
    }
}
