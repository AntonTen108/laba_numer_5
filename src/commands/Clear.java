package commands;

import collection.CollectionManager;
import manager.CommandManager;
import network.Request;
import network.Response;

public class Clear extends AbstractCommand {

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getHelp() {
        return "clear : очистить коллекцию";
    }

    @Override
    public Response execute(Request request,
                            CollectionManager collection,
                            CommandManager manager) {
        collection.clear();
        return Response.ok("Коллекция успешно очищена!");
    }
}