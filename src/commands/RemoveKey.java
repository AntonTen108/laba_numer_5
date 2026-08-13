package commands;

import collection.CollectionManager;
import io.InputSource;
import manager.CommandManager;

public class RemoveKey extends AbstractCommand{

    @Override
    public String getName() {
        return "Remove_key";
    }

    @Override
    public String getHelp() {
        return "remove_key null : удалить элемент из коллекции по его ключу";
    }

    @Override
    public String execute(String [] args, InputSource source, CollectionManager collection, CommandManager manager){
         requireArgs(args, 1);
             int id = parseId(args[0]);
             collection.removeKey(id);
             return "Элемент с id = " + id + " успешно удален из коллекции!";
    }
}
