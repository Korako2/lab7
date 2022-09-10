package handlers;

import lombok.AllArgsConstructor;
import messageUtils.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.concurrent.RecursiveTask;

@AllArgsConstructor
public class RequestHandler extends RecursiveTask<Request> {
    private Socket socket;
    @Override
    protected Request compute()  {
        ObjectInputStream objectInputStream;
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            return (Request) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("The client broke the connection.");
        }
    }
}
