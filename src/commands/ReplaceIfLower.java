package commands;

import collection.CollectionManager;
import manager.CommandManager;
import models.Product;
import network.Request;
import network.Response;

public class ReplaceIfLower extends AbstractCommand {
    @Override
    public String getName() {
        return "replace_if_lower";
    }

    @Override
    public String getHelp() {
        return "replace_if_lower id {element} : заменить значение по ключу, если новое значение меньше старого";
    }

    @Override
    public Response execute(Request request, CollectionManager collection, CommandManager manager) {
        String[] arguments = request.getArguments();
        requireArgs(arguments, 1);

        int id = parseId(arguments[0]);
        Product product = request.getProduct();

        if (product == null) {
            throw new IllegalArgumentException("Объект Product не передан!");
        }

        boolean replaced = collection.replaceIfLower(id, product);

        if (!replaced) {
            return Response.ok("Элемент с id = " + id + " не изменён: новый элемент не меньше текущего.");
        }

        return Response.ok("Элемент с id = " + id + " успешно заменён на меньший.");
    }
}
