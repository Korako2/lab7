package client;

import client.IOutils.UserInputManager;
import lombok.RequiredArgsConstructor;
import sharedClasses.Request;
import sharedClasses.Response;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

@RequiredArgsConstructor
public class Client {
    private final String host;
    private final int port;
    private final PrintStream out;

    private final UserInputManager userInputManager;
    private SocketChannel socketChannel;
    private ObjectOutputStream writer;
    private ObjectInputStream reader;

    private int maxCountOfConnection = 10;
    private int countOfConnections = 0;

    public void run() throws IOException {

        boolean status = true;
        while (status) {
            try {
                connectToServer();
                status = requestToServer();
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
        out.println("Клиент успешно завершил свою работу.");
    }

    private void connectToServer() throws IOException, InterruptedException {
        try {
            out.println("Попытка установить соединение...");
            socketChannel = SocketChannel.open(new InetSocketAddress(host, port));
            socketChannel.configureBlocking(true); //todo
            out.println("Соединение установлено.");
            out.println("Ожидание ответа от сервера на запрос обмена данными.");
            writer = new ObjectOutputStream(socketChannel.socket().getOutputStream());
            reader = new ObjectInputStream(socketChannel.socket().getInputStream());
            out.println("Разрешение на обмен данными получено.");
        } catch (IOException e) {
            out.println("Случилась ошибка при соединении с сервером.");
            if (countOfConnections <= maxCountOfConnection) reconnectToServer();
            throw new IOException("Не удалось установить соединение с сервером.");
        } catch (IllegalArgumentException e) {
            out.println("Хост или порт введены некорректно.");
        }
    }

    private void reconnectToServer() throws IOException, InterruptedException {
        disconnect();
        while (countOfConnections <= maxCountOfConnection) {
            try {
                countOfConnections++;
                out.println(countOfConnections);
                connectToServer();
                out.println("Соединение с сервером установлено.");
            } catch (IOException e) {
                out.println(e.getMessage());
                Thread.sleep(1000);
                countOfConnections++;
                out.println("Попытка повторно подключиться к серверу.");
            } catch (InterruptedException e) {
                out.println(e.getMessage());
            }
        }
    }

    private void disconnect() throws IOException {
        if (socketChannel != null) socketChannel.close();
    }

    private boolean requestToServer() throws IOException, ClassNotFoundException {
        try {
            Request request = null;
            Response response = null;
            do {
                try {
                    request = userInputManager.input();
                    if (request == null) return false;
                    if (request.isEmpty()) continue;
                    writer.writeObject(request);
                    response = (Response) reader.readObject();
                    out.println(response.getResponseHead() + " " +response.getResponseBody());
                } catch (IllegalArgumentException e) {
                    out.println(e.getMessage());
                }
            } while (!request.getNameOfCommand().toUpperCase().equals("EXIT"));
            return false;
        } catch (IOException e) {
            throw new IOException("Соединение с сервером разорвано.");
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("Произошла ошибка при чтении данных.");
        }
    }

}
