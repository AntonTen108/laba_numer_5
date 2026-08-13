package manager;

import collection.CollectionManager;
import commands.Command;
import io.InputSource;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CommandManager {

    private Map<String, Command> commands = new HashMap<>();

    public void register (Command command) {
        commands.put(command.getName(),command);
    }

    public Collection<Command> getCommand() {
        return commands.values();
    }

    public String handle(String input, InputSource source, CollectionManager manager) {
        String[] parts = input.split(" ", 2);
        String name = parts[0];
        String line = (parts.length > 1) ? parts[1] : "";
        String[] args = line.isEmpty() ? new String[0] : line.split(" ");

        Command command = commands.get(name);

        if (command == null) {
            return " Команда не найдена " + name + "!";
        }
        try {
            return command.execute(args, source, manager, this);
        } catch (RuntimeException e) {
            return " Ошибка " + e.getMessage() + "!";
        }

    }
}
