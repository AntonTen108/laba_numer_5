package collection;

import exceptions.CollectionIsEmpty;
import exceptions.ValidationException;
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
            throw new ValidationException("Элемент id = "+ id + " уже существует!");
        }
        collection.put(id, product);
    }

    public void update(int id, Product newData) {
        if (!collection.containsKey(id)) {
            throw new ValidationException("Элемента id = " + id + " не существует, нельзя обновить!");
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
            throw new ValidationException("Элемента id = " + id + " не существует, нельзя удалить!");
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
            throw new ValidationException("Элемента id = " + id + " не существует, нельзя изменить на большее!");
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
            throw new ValidationException("Элемента id = " + id + " не существует, нельзя изменить на меньшее!");
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
            throw new CollectionIsEmpty("Коллекция пустая, нельзя найти минимум по полю owner!");
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

    public List<Product> filterGreaterThanOwner(Person threshold) {
        List<Product> result = new ArrayList<>();
        for (Product product : collection.values()) {
            if (product.getOwner().getName().compareTo(threshold.getName()) > 0) {
                result.add(product);
            }
        }
        return result;
    }

}
