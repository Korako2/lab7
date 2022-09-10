package handlers;

import collectionUtil.CollectionManager;
import commands.commandsUtils.ArgObjectForServer;
import commands.commandsUtils.CommandResult;
import lombok.AllArgsConstructor;
import messageUtils.Request;
import messageUtils.Response;

import java.util.concurrent.RecursiveTask;
@AllArgsConstructor
public class ResponseHandler extends RecursiveTask<Response> {
    private CollectionManager collectionManager;
    private Request request;
    @Override
    protected Response compute() {
        ArgObjectForServer argObject = new ArgObjectForServer(collectionManager, request.getArgsOfCommand(),
                request.getMusicBand(), request.getAccount().getUserName());
        CommandResult commandResult = request.getCommand().execute(argObject);
        return new Response(commandResult.getResponseCode(), commandResult.getResult());
    }
}
