package io;

import java.io.*;
/**
 * класс для чтения данных из файла со скриптом
 * реализует интерфейс InputSource
 * используется командой execute_script для выполнения команд из файла
 */
public class ScriptInput implements InputSource {
    /**
     * имя файла со скриптом
     */
    private String fileName;
    /**
     * объект для чтения строк из файла
     */
    private BufferedReader reader;
    /**
     * создаёт объект для чтения скрипта из файла
     * создаёт BufferedReader, который читает данные из указанного файла
     *
     * @param fileName имя файла со скриптом
     * @throws RuntimeException если файл не найден
     */
    public ScriptInput (String fileName) {
        this.fileName = fileName;
        try {
            this.reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Файл не найден !" + e.getMessage());
        }
    }
    /**
     * считывает следующую строку из файла со скриптом
     *
     * @return следующая строка файла или null если файл закончился
     * @throws RuntimeException если произошла ошибка при чтении файла
     */
    @Override
    public String nextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения текста файла! " + e.getMessage());
        }
    }
    /**
     * метод пустой, т.к подсказка при чтении из скрипта бесмыслена
     * @param fieldName название поля
     */
    @Override
    public void clue(String fieldName) { }
    /*
    класс отвечает за чтение данных из скрипта

    поле filename - хранит имя файла
    поле reader - читает строки из файла

    метод nextLine читает следующую строку файла

    метод clue ничего не делает тк подсказки файлу не нужны
     */
}

