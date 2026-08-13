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

}

