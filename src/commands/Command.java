package commands;

import collection.CollectionManager;
import io.InputSource;
import manager.CommandManager;

public interface Command {
    String getName();
    String getHelp();
    String execute (String [] args,
                    InputSource source,
                    CollectionManager collection,
                    CommandManager manager);
}

//String [] args, InputSource source, CollectionManager collection, CommandManager manager