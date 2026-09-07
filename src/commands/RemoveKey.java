package commands;

import collection.CollectionManager;
import io.InputSource;
import manager.CommandManager;
import network.Request;
import network.Response;

public class RemoveKey extends AbstractCommand{

    @Override
    public String getName() {
        return "remove_key";
    }

    @Override
    public String getHelp() {
        return "remove_key null : удалить элемент из коллекции по его ключу";
    }

    @Override
    public Response execute(Request request, CollectionManager collection, CommandManager manager){
        String[] args = request.getArguments();
        requireArgs(args, 1);
             int id = parseId(args[0]);
             collection.removeKey(id);
             return Response.ok("Элемент с id = " + id + " успешно удален из коллекции!");
    }
}
