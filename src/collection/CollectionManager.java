package collection;

import models.*;
import java.time.LocalDateTime;
import java.util.*;

public class CollectionManager {
    private TreeMap<Integer, Product> collection;
    private LocalDateTime initializationDate;


    public CollectionManager() {
        this.collection = new TreeMap<>();
        this.initializationDate = LocalDateTime.now();
    }

    public String info() {
        return "Тип = " + collection.getClass().getName() + "\n дата инициализации = "+ initializationDate + "\n количество элементов = " + collection.size();
    }

    public List<Product> show() {
        return new ArrayList<>(collection.values());
    }

    public void insert(int id, Product product) {
        if (collection.containsKey(id)) {
            throw new RuntimeException("Элемент id = "+ id + " уже существует!");
        }
        collection.put(id, product);
    }

    public void update(int id, Product newData) {
        if (!collection.containsKey(id)) {
            throw new RuntimeException("Элемента id = " + id + " не существует, нельзя обновить!");
        }
        Product exist = collection.get(id);

        exist.setName(newData.getName());
        exist.setCoordinates(newData.getCoordinates());
        exist.setPrice(newData.getPrice());
        exist.setPartNumber(newData.getPartNumber());
        exist.setUnitOfMeasure(newData.getUnitOfMeasure());
        exist.setOwner(newData.getOwner());
    }


    public void removeKey(int id) {
        if (!collection.containsKey(id)) {
            throw new RuntimeException("Элемента id = " + id + " не существует, нельзя удалить!");
        }
        collection.remove(id);
    }

    public void clear() {
        collection.clear();
    }

    public void removeLower(Product lower) {
        List<Integer> toRemove = new ArrayList<>();

        for (Product product : collection.values()) {
            if (product.compareTo(lower) < 0) {
                toRemove.add(product.getId());
            }
        }
        for (int id : toRemove) {
            collection.remove(id);
        }
    }
    public void replaceIfGreater(int id, Product newProduct) {
        if (!collection.containsKey(id)) {
            throw new RuntimeException("Элемента id = " + id + " не существует, нельзя изменить на большее!");
        }
        Product exist = collection.get(id);
        int i = newProduct.compareTo(exist);

        if (i > 0) {
            exist.setName(newProduct.getName());
            exist.setCoordinates(newProduct.getCoordinates());
            exist.setPrice(newProduct.getPrice());
            exist.setOwner(newProduct.getOwner());
            exist.setPartNumber(newProduct.getPartNumber());
            exist.setUnitOfMeasure(newProduct.getUnitOfMeasure());
        }
    }

    public void replaceIfLower(int id, Product newProduct) {
        if (!collection.containsKey(id)) {
            throw new RuntimeException("Элемента id = " + id + " не существует, нельзя изменить на меньшее!");
        }
        Product exist = collection.get(id);
        int i = newProduct.compareTo(exist);

        if (i < 0) {
            exist.setName(newProduct.getName());
            exist.setCoordinates(newProduct.getCoordinates());
            exist.setPrice(newProduct.getPrice());
            exist.setOwner(newProduct.getOwner());
            exist.setPartNumber(newProduct.getPartNumber());
            exist.setUnitOfMeasure(newProduct.getUnitOfMeasure());
        }
    }

    public Product minByOwner() {
        if (collection.isEmpty()) {
            throw new RuntimeException("Коллекция пустая, нельзя найти минимум по полю owner!");
        }
        return collection.values().stream().min(Comparator.comparing(product -> product.getOwner().getName())).get();
    }


    public List<Product> filterContainsName(String namePart) {
        List<Product> result = new ArrayList<>();
        for (Product product : collection.values()) {
            if (product.getName().contains(namePart)) {
                result.add(product);
            }
        }
        return result;
    }

    public List<Product> filterGreaterThanOwner(Person owner) {
        List<Product> result = new ArrayList<>();
        for (Product product : collection.values()) {
            if (product.getOwner().getName().compareTo(owner.getName()) > 0) {
                result.add(product);
            }
        }
        return result;
    }
    /*
    класс создает 2 поля: коллекцию и инициализированную дату

    метод info возвращает тип, дату инициализации и количество элементов коллекции

    метод show возвращает массив значений коллекции

    метод insert добавляет в коллекцию новый элемент, осуществляет проверку на различие id

    метод update проверяет, существует ли входной id, если есть то все параметры элемента с данным id подвергаются изменению кроме id и creationDate

    метод removeKey существует ли входной id, если да то объект коллекции с данным id удаляется

    метод clear удаляет все элементы коллекции

    метод replaceIfGreater проверяет есть ли элемент с данным id, сравнивает цену нового объекта с уже существующем, если цена нового объекта больше то его значения переходят в объект с меньшей ценой

    метод replaceIfLower делает все тоже, самое что и replaceIfGreater только наоборот

    метод minByOwner проверяет пустая ли коллекция, если нет, то возвращает минимальный элемент по переменной owner.

    метод filterContainsName создает новый массив продуктов, через цикл проходит по каждому элемента массива, если переменная name содержит подстроку name part этот элемент добавляется в только, что созданный список, он же и выводится

    метод filterGreaterThanOwner делает практически тоже, самое что и метод filterContainsName зв исключением того, что он не смотрит, содержится ли задаваемое имя во всех элементах массива и сравнивает входное имя со всеми, что есть в коллекции
     */
}
