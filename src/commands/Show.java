package commands;

import collection.CollectionManager;
import io.InputSource;
import manager.CommandManager;
import models.Product;
import network.Request;
import network.Response;

import java.util.List;

public class Show extends AbstractCommand {

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getHelp() {
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

    @Override
    public Response execute(Request request, CollectionManager collection, CommandManager manager) {
        List<Product> products = collection.show();
        return productList(products, "коллекция пуста!");
    }

}
