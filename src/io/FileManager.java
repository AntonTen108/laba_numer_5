package io;

import collection.CollectionManager;
import exceptions.CsvParseException;
import models.Product;

import java.io.*;
import java.util.Collection;

public class FileManager {

    private final CSVmaker csvMapper = new CSVmaker();

    public void saveCollection(String filename, Collection<Product> products) {
        try (FileOutputStream fos = new FileOutputStream(filename)) {
            for (Product p : products) {
                String line = csvMapper.toCSV(p) + System.lineSeparator();
                fos.write(line.getBytes());

            }
            System.out.println("Коллекция сохранена в файл: " + filename);
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }
    }

    public void loadCollection (String filename, CollectionManager manager) {
        File file = new File(filename);

        if (!file.exists()) {
            System.out.println("Файл " + filename + " не найден!");
            return;
        }
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
             BufferedReader reader = new BufferedReader(isr)) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }
                try {
                    Product product = csvMapper.fromCsv(line);
                    manager.insert(product.getId(), product);
            } catch (CsvParseException e) {
                    System.out.println("Передано некорректное значение " + e.getMessage() + " строка пропущена!");
                }
        }

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден! " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла! " + e.getMessage());
        }
    }
}
