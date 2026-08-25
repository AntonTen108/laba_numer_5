package io;

import java.io.*;

public class ScriptInput implements InputSource {
    private String fileName;
    private BufferedReader reader;

    public ScriptInput (String fileName) {
        this.fileName = fileName;
        try {
            this.reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Файл не найден !" + e.getMessage());
        }
    }

    @Override
    public String nextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения текста файла! " + e.getMessage());
        }
    }

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

