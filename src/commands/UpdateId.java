package commands;

import collection.CollectionManager;
import io.InputSource;
import io.ProductAsker;
import manager.CommandManager;
import models.Product;

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
    public String execute(String [] args, InputSource source, CollectionManager collection, CommandManager manager) {
        requireArgs(args, 1);
        int id = parseId(args[0]);
        ProductAsker askP = createAsker(source);
        Product product = askP.askProduct(source);
        collection.update(id, product);
        return "Элемент с id = " + id + " успешно обновлен!";
    }
}
