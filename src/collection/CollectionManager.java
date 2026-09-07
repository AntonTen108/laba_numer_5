package collection;

import models.Person;
import models.Product;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CollectionManager {
    private final TreeMap<Integer, Product> collection;
    private final LocalDateTime initializationDate;

    public CollectionManager() {
        collection = new TreeMap<>();
        initializationDate = LocalDateTime.now();
    }

    public String info() {
        return "Тип = " + collection.getClass().getName() + "\nДата инициализации = " + initializationDate + "\nКоличество элементов = " + collection.size();
    }

    public List<Product> show() {
        return collection.values().stream().sorted(Comparator.comparing(Product::getName)).toList();
    }

    public void insert(int id, Product product) {
        if (collection.containsKey(id)) {
            throw new IllegalArgumentException("Элемент с id = " + id + " уже существует!");
        }

        collection.put(id, product);
    }

    public void update(int id, Product newData) {
        Product existingProduct = getProduct(id);
        copyProductData(existingProduct, newData);
    }

    public void removeKey(int id) {
        if (!collection.containsKey(id)) {
            throw new IllegalArgumentException("Элемента с id = " + id + " не существует!");
        }

        collection.remove(id);
    }

    public void clear() {
        collection.clear();
    }

    public void removeLower(Product product) {
        List<Integer> keysToRemove = collection.entrySet().stream().filter(entry -> entry.getValue().compareTo(product) < 0).map(Map.Entry::getKey).toList();
        keysToRemove.forEach(collection::remove);
    }

    public boolean replaceIfGreater(int id, Product newProduct) {
        Product existingProduct = getProduct(id);

        if (newProduct.compareTo(existingProduct) <= 0) {
            return false;
        }

        copyProductData(existingProduct, newProduct);
        return true;
    }

    public boolean replaceIfLower(int id, Product newProduct) {
        Product existingProduct = getProduct(id);

        if (newProduct.compareTo(existingProduct) >= 0) {
            return false;
        }

        copyProductData(existingProduct, newProduct);
        return true;
    }

    public Product minByOwner() {
        return collection.values().stream().min(Comparator.comparing(product -> product.getOwner().getName())).orElseThrow(() -> new IllegalStateException("Коллекция пуста, нельзя найти минимум по полю owner!"));
    }

    public List<Product> filterContainsName(String namePart) {
        return collection.values().stream().filter(product -> product.getName().contains(namePart)).sorted(Comparator.comparing(Product::getName)).toList();
    }

    public List<Product> filterGreaterThanOwner(Person owner) {
        return collection.values().stream().filter(product -> product.getOwner().getName().compareTo(owner.getName()) > 0).sorted(Comparator.comparing(Product::getName)).toList();
    }

    private Product getProduct(int id) {
        Product product = collection.get(id);

        if (product == null) {
            throw new IllegalArgumentException("Элемента с id = " + id + " не существует!");
        }

        return product;
    }

    private void copyProductData(Product destination, Product source) {
        destination.setName(source.getName());
        destination.setCoordinates(source.getCoordinates());
        destination.setPrice(source.getPrice());
        destination.setPartNumber(source.getPartNumber());
        destination.setUnitOfMeasure(source.getUnitOfMeasure());
        destination.setOwner(source.getOwner());
    }
}