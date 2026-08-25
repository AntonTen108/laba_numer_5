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
/*
интерфейс Commands наследуют все классы команд.
метод getName нужен для того чтобы, передавать имя команды
метод getHelp нужен для того чтобы, передавать функционал команды
метод execute должен реализовывать логику команды в интерактивном режиме
*/