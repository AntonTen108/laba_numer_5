package commands;

import collection.CollectionManager;
import manager.CommandManager;
import models.Product;
import network.Request;
import network.Response;

public class Insert extends AbstractCommand {

    @Override
    public String getName() {
        return "insert";
    }

    @Override
    public String getHelp() {
        return "insert null {element} : добавить новый элемент";
    }

    @Override
    public Response execute(Request request,
                            CollectionManager collection,
                            CommandManager manager) {
        Product receivedProduct = request.getProduct();

        if (receivedProduct == null) {
            throw new IllegalArgumentException(
                    "Объект Product не передан!"
            );
        }

        Product serverProduct = new Product(
                receivedProduct.getName(),
                receivedProduct.getCoordinates(),
                receivedProduct.getPrice(),
                receivedProduct.getPartNumber(),
                receivedProduct.getUnitOfMeasure(),
                receivedProduct.getOwner()
        );

        collection.insert(serverProduct.getId(), serverProduct);

        return Response.ok(
                "Элемент с id = " + serverProduct.getId()
                        + " добавлен в коллекцию!"
        );
    }
}
