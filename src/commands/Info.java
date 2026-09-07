package commands;

import collection.CollectionManager;
import io.InputSource;
import manager.CommandManager;
import network.Request;
import network.Response;

public class Info extends AbstractCommand {

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getHelp() {
        return "info : вывести в стандартный поток вывода информацию о коллекции";
    }

    @Override
    public Response execute(Request request, CollectionManager collection, CommandManager manager) {
        return Response.ok(collection.info());
    }
}
