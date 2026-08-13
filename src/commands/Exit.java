package commands;

import collection.CollectionManager;
import io.InputSource;
import manager.CommandManager;

public class Exit extends AbstractCommand {

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getHelp() {
        return "exit : завершить программу (без сохранения в файл)";
    }

    @Override
    public String execute(String [] args, InputSource source, CollectionManager collection, CommandManager manager) {
        System.exit(0);
        return "Программа завершена!";
    }
}
