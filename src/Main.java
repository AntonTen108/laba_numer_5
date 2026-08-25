import collection.CollectionManager;
import commands.*;
import io.FileManager;
import io.InteractiveInput;
import manager.CommandManager;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("не указано имя файла!");
            return;
        }

        String fileName = args[0];

        CollectionManager collectionManager = new CollectionManager();
        FileManager fileManager = new FileManager();
        fileManager.loadCollection(fileName, collectionManager);

        CommandManager commandManager = new CommandManager();
        commandManager.register(new Help());
        commandManager.register(new Info());
        commandManager.register(new Show());
        commandManager.register(new Insert());
        commandManager.register(new UpdateId());
        commandManager.register(new RemoveKey());
        commandManager.register(new Clear());
        commandManager.register(new Save(fileManager, fileName));
        commandManager.register(new ExecuteScript());
        commandManager.register(new Exit());
        commandManager.register(new RemoveLower());
        commandManager.register(new ReplaceIfGreater());
        commandManager.register(new ReplaceIfLower());
        commandManager.register(new MinByOwner());
        commandManager.register(new FilterContainsName());
        commandManager.register(new FilterGreaterThanOwner());

        InteractiveInput consoleSource = new InteractiveInput();

        System.out.println("Программа запущенна, введите 'help' для просмотра доступных команд!");

        while (true) {
            String input = consoleSource.nextLine();
            if (input == null) {
                break;
            }
            if (input.isBlank()) {
                continue;
            }
            String res = commandManager.handle(input, consoleSource, collectionManager);
            System.out.println(res);
        }
    }
}
/*
первым делом идет проверка передано ли имя файла

далее создается менеджер коллекции и коллекция загружается из файла

создается менеджер команд и регистрируется введенные доступных команд

команда переходит в интерактивный режим, читает данные из консоли и выводит результат
 */