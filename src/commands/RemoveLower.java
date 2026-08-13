package commands;

import collection.CollectionManager;
import io.InputSource;
import io.ProductAsker;
import manager.CommandManager;
import models.Product;

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
    public String execute(String [] args, InputSource source, CollectionManager collection, CommandManager manager) {
        ProductAsker askP = createAsker(source);
        Product product = askP.askProduct(source);
        collection.removeLower(product);
        return "Элементы с ценой меньше чем  " + product.getPrice() + " удалены из коллекции!";
    }
}
