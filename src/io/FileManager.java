package io;

import collection.CollectionManager;
import models.Product;

import java.io.*;
import java.util.Collection;
/**
 * класс для сохранения и загрузки коллекции из файла
 * использует CSVmaker, чтобы преобразовывать объекты Product в строки CSV и обратно
 */
public class FileManager {
    /**
     * объект для преобразования Product в формат CSV
     */
    private final CSVmaker csvMapper = new CSVmaker();
    /**
     * сохраняет коллекцию Product в файл
     * каждый объект Product преобразуется в строку CSV
     * затем строка записывается в файл
     *
     * @param filename имя файла для сохранения
     * @param products коллекция объектов Product
     */
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
    /**
     * загружает объекты Product из файла в коллекцию
     * файл читается построчно
     * каждая строка преобразуется в Product и добавляется в CollectionManager
     *
     * @param filename имя файла из которого нужно загрузить коллекцию
     * @param manager менеджер коллекции куда будут добавлены объекты
     */
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
            } catch (RuntimeException e) {
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
/*
класс нужен для загрузки сохранения коллекции из файла

метод saveCollection сохраняет коллекцию в файл

метод loadCollection загружает продукты из файла в менеджер коллекции
 */