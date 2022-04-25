package server;

import lombok.RequiredArgsConstructor;
import server.collectionUtil.CollectionManager;
import sharedClasses.Request;
import sharedClasses.Response;
import sharedClasses.commands.Command;
import sharedClasses.commands.Save;
import sharedClasses.commands.commandsUtils.ArgObject;
import sharedClasses.commands.commandsUtils.CommandsManager;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

@RequiredArgsConstructor
public class Server {
    private final int port;

    private final CollectionManager collectionManager;

    private final CommandsManager commandsManager;
    private ServerSocket serverSocket;

    public void run() throws IOException {
        boolean status = true;
        openServerSocket();
        while (status) {
            try {
                Socket clientSocket = connectToClient();
                status = processClientRequest(clientSocket);
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            Save save = (Save) commandsManager.getCommand("SAVE");
            ArgObject argObject = new ArgObject(collectionManager,null, null, commandsManager);
            System.out.println(save.execute(argObject));
            exit();
            run();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void openServerSocket() throws IOException {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new IOException("Не удалось открыть ServerSocket");
        }
    }

    private void exit() throws IOException {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
            System.out.println("Соединение с клиентом разорвано.");

        } catch (IOException e) {
            throw new IOException("Ошибка при завершении соединения с клиентом.");
        }
    }

    private Socket connectToClient() throws IOException {
        Socket clientSocket = serverSocket.accept();
        System.out.println("Соединение с клиентом успешно установлено.");
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
                ArgObject argObject = new ArgObject(collectionManager, request.getArgsOfCommand(), request.getMusicBand(), commandsManager);
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
