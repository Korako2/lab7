package client;

import client.IOutils.UserInputManager;
import client.commands.ArgObjectForClient;
import client.commands.ClientCommandsManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sharedClasses.messageUtils.Request;
import sharedClasses.messageUtils.Response;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

@RequiredArgsConstructor
public class Client {
    private final String host;
    private final int port;
    private final PrintStream out;
    private final UserInputManager userInputManager;
    private final ClientCommandsManager clientCommandsManager;
    private SocketChannel socketChannel;
    @Getter
    private ObjectOutputStream writer;
    @Getter
    private ObjectInputStream reader;
    private int maxCountOfConnection = 10;
    private int countOfConnections = 0;

    public void run() throws IOException {

        boolean statusOfRequest = true;
        boolean statusOfConnection = false;
        while (statusOfRequest && countOfConnections <= maxCountOfConnection) {
            try {
                statusOfConnection = connectToServer();
                if (statusOfConnection) statusOfRequest = requestToServer(userInputManager);
            } catch (IOException e) {
                out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                out.println(e.getMessage());
            } catch (ClassNotFoundException e) {
                out.println(e.getMessage());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        disconnect();
        out.println("Клиент завершил свою работу.");
    }

    private boolean connectToServer() throws IOException, InterruptedException {
        disconnect();
        countOfConnections++;
        try {
            out.println("Попытка установить соединение...");
            socketChannel = SocketChannel.open(new InetSocketAddress(host, port));
            socketChannel.configureBlocking(true); //todo
            out.println("Соединение установлено.");
            out.println("Ожидание ответа от сервера на запрос обмена данными.");
            writer = new ObjectOutputStream(socketChannel.socket().getOutputStream());
            reader = new ObjectInputStream(socketChannel.socket().getInputStream());
            out.println("Разрешение на обмен данными получено.");
            countOfConnections = 0;
            return true;
        } catch (IOException e) {
            out.println("Случилась ошибка при соединении с сервером.");
            Thread.sleep(5000);
            return false;
            //throw new IOException("Не удалось установить соединение с сервером.");
        } catch (IllegalArgumentException e) {
            out.println("Хост или порт введены некорректно.");
            return false;
        }
    }

    private void disconnect() throws IOException {
        if (socketChannel != null) socketChannel.close();
    }

    public boolean requestToServer(UserInputManager userInputManager) throws IOException, ClassNotFoundException,IllegalArgumentException {
        try {
            Request request = null;
            Response response = null;
            do {
                try {
                    request = userInputManager.input();
                    if (request == null) return false;
                    if (request.getCommand().isServer()) {
                        writer.writeObject(request);
                        response = (Response) reader.readObject();
                        out.println(response.getResponseBody());
                    } else {
                        ArgObjectForClient argObject = new ArgObjectForClient(clientCommandsManager, request.getArgsOfCommand(), null);
                        out.println(request.getCommand().execute(argObject));
                    }
                    } catch (IllegalArgumentException e) {
                    out.println(e.getMessage());
                }
            } while (request == null || !request.getNameOfCommand().equals("EXIT"));
            return false;
        }
        catch (IOException e) {
            throw new IOException("Соединение с сервером разорвано.");
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("Произошла ошибка при чтении данных.");
        }
    }

}
