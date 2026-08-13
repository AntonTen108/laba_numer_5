package commands;

import collection.CollectionManager;
import io.InputSource;
import io.ProductAsker;
import manager.CommandManager;
import models.Product;

public class ReplaceIfGreater extends AbstractCommand {

    @Override
    public String getName() {
        return "replace_if_greater";
    }

    @Override
    public String getHelp() {
        return "replace_if_greater null {element} : заменить значение по ключу, если новое значение больше старого";
    }

    @Override
    public String execute(String [] args, InputSource source, CollectionManager collection, CommandManager manager) {
        requireArgs(args, 1);
        int id = parseId(args[0]);
        ProductAsker askP = createAsker(source);
        Product product = askP.askProduct(source);
        collection.replaceIfGreater(id, product);
        return "элемент с id = " + id + " успешно изменен на другой с большей ценой!";
    }
}
