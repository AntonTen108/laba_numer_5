package commands;

import collection.CollectionManager;
import io.InputSource;
import io.ProductAsker;
import manager.CommandManager;
import models.Product;
import network.Request;
import network.Response;

public class RemoveLower extends AbstractCommand {

    @Override
    public String getName() {
        return "remove_lower";
    }

    @Override
    public String getHelp() {
        return "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный";
    }

    @Override
    public Response execute(Request request, CollectionManager collection, CommandManager manager) {
        Product p = request.getProduct();

        if (p == null) {
            throw new IllegalArgumentException("Объект Product не передан!");
        }

        collection.removeLower(p);
        return Response.ok("Элементы с ценой меньше чем  " + p.getPrice() + " удалены из коллекции!");
    }
}
