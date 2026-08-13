package commands;

import collection.CollectionManager;
import io.InputSource;
import io.ProductAsker;
import manager.CommandManager;
import models.Person;
import models.Product;

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
    public String execute(String [] args, InputSource source, CollectionManager collection, CommandManager manager) {
        ProductAsker per = createAsker(source);
        Person owner = per.askPerson(source);
        List<Product> products = collection.filterGreaterThanOwner(owner);
        return productList(products, "Совпадений не найдено!");

    }
}
