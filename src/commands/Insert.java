package commands;

import collection.CollectionManager;
import io.InputSource;
import io.ProductAsker;
import manager.CommandManager;
import models.Product;

public class Insert extends AbstractCommand{

    @Override
    public String  getName() {
        return "insert";
    }

    @Override
    public String getHelp() {
        return "insert null {element} : добавить новый элемент с заданным ключом";
    }

    @Override
    public String execute(String [] args, InputSource source, CollectionManager collection, CommandManager manager) {
        ProductAsker askP = createAsker(source);
        Product product = askP.askProduct(source);
        collection.insert(product.getId(), product);
        return "Элемент с id = " +product.getId() + " добавлен в коллекцию!";
    }
}
