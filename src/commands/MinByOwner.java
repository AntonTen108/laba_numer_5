package commands;

import collection.CollectionManager;
import manager.CommandManager;
import models.Product;
import network.Request;
import network.Response;

import java.util.Collections;

public class MinByOwner extends AbstractCommand{

    @Override
    public String getName() {
        return "min_by_owner";
    }

    @Override
    public String getHelp() {
        return "min_by_owner : вывести любой объект из коллекции, значение поля owner которого является минимальным";
    }

    @Override
    public Response execute(Request request, CollectionManager collection, CommandManager manager) {
        Product p =  collection.minByOwner();
        return Response.ok("", Collections.singletonList(p));
    }
}
