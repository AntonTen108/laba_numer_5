package commands;

import collection.CollectionManager;
import io.InputSource;
import manager.CommandManager;

import java.util.Collection;

public class Help extends AbstractCommand {

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getHelp() {
        return "help : вывести справку по доступным командам";
    }

    @Override
    public String execute(String [] args, InputSource source, CollectionManager collection, CommandManager manager) {
        Collection<Command> allHelp = manager.getCommand();
        String res = "";
        for (Command c : allHelp) {
            res += c.getHelp() + "\n";
        }
        return res;
    }
}
