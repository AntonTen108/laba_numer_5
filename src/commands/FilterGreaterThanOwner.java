package commands;

import collection.CollectionManager;
import io.InputSource;
import io.ProductAsker;
import manager.CommandManager;
import models.Person;
import models.Product;
import network.Request;
import network.Response;

import java.util.List;

public class FilterGreaterThanOwner extends AbstractCommand {

    @Override
    public String getName() {
        return "filter_greater_than_owner";
    }

    @Override
    public String getHelp() {
        return "filter_greater_than_owner owner : вывести элементы, значение поля owner которых больше заданного";
    }

    @Override
    public Response execute(Request request, CollectionManager collection, CommandManager manager) {
        Person owner = request.getOwner();
        if (owner == null) {
            throw new IllegalArgumentException("Объект Person не передан!");
        }
        List<Product> products = collection.filterGreaterThanOwner(owner);
        return productList(products, "Совпадений не найдено!");

    }
}
