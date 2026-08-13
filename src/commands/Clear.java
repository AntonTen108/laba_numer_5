package commands;

import collection.CollectionManager;
import io.InputSource;
import manager.CommandManager;

public class Clear extends AbstractCommand {

    public String getName() {
        return "clear";
    }

    public String getHelp() {
        return "clear : очистить коллекцию";
    }

    public String execute(String [] args, InputSource source, CollectionManager collection, CommandManager manager) {
        collection.clear();
        return "Коллекция успешно очищена!";
    }
}
