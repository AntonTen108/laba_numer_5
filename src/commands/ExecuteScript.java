package commands;

import collection.CollectionManager;
import exceptions.ValidationException;
import io.InputSource;
import io.ScriptInput;
import manager.CommandManager;

import java.util.Stack;

public class ExecuteScript extends AbstractCommand {

    private Stack<String> runningScripts = new Stack<>();

    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public String getHelp() {
        return "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }

    @Override
    public String execute(String [] args, InputSource source, CollectionManager collection, CommandManager manager) {
        requireArgs(args, 1);
        String filename = args[0];

        if (runningScripts.contains(filename)) {
            throw new ValidationException("Рекурсия! Скрипт " + filename + " уже выполняется!");
        }

        runningScripts.push(filename);
        try {
            ScriptInput scriptInput = new ScriptInput(filename);
            String line;
            StringBuilder res = new StringBuilder();
            while ((line = scriptInput.nextLine()) != null) {
                if (!line.isBlank()) {
                    res.append(manager.handle(line, scriptInput, collection)).append("\n");
                }
            }
            return res.toString();
        }finally {
            runningScripts.pop();
        }

    }
}
