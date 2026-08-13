package commands;

import collection.CollectionManager;
import io.InputSource;
import manager.CommandManager;
import models.Product;

public class MinByOwner extends AbstractCommand{

    @Override
    public String getName() {
        return "min_by_owner";
    }

    @Override
    public String getHelp() {
        return "min_by_owner : вывести любой объект из коллекции, значение поля owner которого является минимальным";
    }

    @Override
    public String execute(String [] args, InputSource source, CollectionManager collection, CommandManager manager) {
        Product p =  collection.minByOwner();
        return p.toString();
    }
}
