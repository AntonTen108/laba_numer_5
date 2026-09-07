package commands;

import collection.CollectionManager;
import manager.CommandManager;
import network.Request;
import network.Response;

/**
 * интерфейс для всех команд программы
 * каждый класс команды должен реализовать этот интерфейс
 */
public interface Command {
    /**
     * возвращает название команды
     *
     * @return название команды
     */
    String getName();
    /**
     * возвращает описание команды для справки
     *
     * @return строка с описанием команды
     */
    String getHelp();


    Response execute(Request request,
                     CollectionManager manager,
                     CommandManager commandManager);
}
/*
интерфейс Commands наследуют все классы команд.
метод getName нужен для того чтобы, передавать имя команды
метод getHelp нужен для того чтобы, передавать функционал команды
метод execute должен реализовывать логику команды в интерактивном режиме
*/