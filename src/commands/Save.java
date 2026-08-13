package commands;

import collection.CollectionManager;
import io.FileManager;
import io.InputSource;
import manager.CommandManager;

public class Save extends AbstractCommand {

    private final FileManager fileManager;
    private final String fileName;

    public Save(FileManager fileManager, String fileName) {
        this.fileManager = fileManager;
        this.fileName = fileName;
    }

    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getHelp() {
        return "save : сохранить коллекцию в файл";
    }

    @Override
    public String execute(String[] args, InputSource source, CollectionManager collection, CommandManager manager) {
        fileManager.saveCollection(fileName, collection.show());
        return "Коллекция успешно сохранена в файл " + fileName;
    }
}

