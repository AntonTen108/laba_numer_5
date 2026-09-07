package commands;

import collection.CollectionManager;
import io.InputSource;
import manager.CommandManager;
import network.Request;
import network.Response;

import java.util.Collection;
import java.util.stream.Collectors;

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
    public Response execute(Request request, CollectionManager collection, CommandManager manager) {
        String text = manager.getCommands().stream().map(Command::getHelp).collect(Collectors.joining(System.lineSeparator()));
        return Response.ok(text);
    }
}
