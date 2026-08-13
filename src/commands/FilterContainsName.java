package commands;

import collection.CollectionManager;
import io.InputSource;
import manager.CommandManager;
import models.Product;

import java.util.List;

public class FilterContainsName extends AbstractCommand {

    @Override
    public String getName() {
        return "filter_contains_name";
    }

    @Override
    public String getHelp() {
        return "filter_contains_name name : вывести элементы, значение поля name которых содержит заданную подстроку";
    }

    @Override
    public String execute(String [] args, InputSource source, CollectionManager collection, CommandManager manager) {
        requireArgs(args, 1);
        String name = args[0];
        List<Product> products = collection.filterContainsName(name);
       return productList(products, "Совпадений не найдено!");
    }
}
