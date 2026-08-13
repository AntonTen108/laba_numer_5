package commands;

import collection.CollectionManager;
import io.InputSource;
import manager.CommandManager;

public class Info extends AbstractCommand {

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getHelp() {
        return "info : вывести в стандартный поток вывода информацию о коллекции";
    }

    @Override
    public String execute(String [] args, InputSource source, CollectionManager collection, CommandManager manager) {
        return collection.info();
    }
}
