package commands;

import collection.CollectionManager;
import io.InputSource;
import io.ProductAsker;
import manager.CommandManager;
import models.Product;
import network.Request;
import network.Response;

public class UpdateId extends AbstractCommand {

    @Override
    public String getName() {
        return "Update";
    }

    @Override
    public String getHelp() {
        return "update id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }

    @Override
    public Response execute(Request request, CollectionManager collection, CommandManager manager) {
        String[] args = request.getArguments();
        requireArgs(args, 1);
        int id = parseId(args[0]);
        Product product = request.getProduct();

        if (product == null) {
            throw new IllegalArgumentException("Объект Product не передан!");
        }
        collection.update(id, product);

        return Response.ok("Элемент с id = " + id + " успешно обновлен!");
    }
}
