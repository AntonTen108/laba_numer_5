package models;

import java.util.Date;
import java.util.Objects;

public class Product implements  Comparable<Product> {
    private  int id;//Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private static int lastId = 0;
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long price; //Значение поля должно быть больше 0
    private String partNumber; //Длина строки должна быть не меньше 14, Длина строки не должна быть больше 79, Поле не может быть null
    private UnitOfMeasure unitOfMeasure; //Поле может быть null
    private Person owner; //Поле не может быть null

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if ( name == null) {
            throw new RuntimeException("Поле name не может быть null!");
        }
        if(name.isEmpty()) {
            throw new RuntimeException("Поле name не может быть пустым!");
        }
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null){
            throw new RuntimeException("Поле coordinates не может быть null!");
        }
        this.coordinates = coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }


    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        if (price <= 0){
            throw new RuntimeException("поле price должно быть больше 0!");
        }
        this.price = price;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        if ( partNumber == null) {
            throw new RuntimeException("Поле partnumber не должно быть null!");
        }
        if (partNumber.length() < 14) {
            throw new RuntimeException("Длина строки partnumber должна быть не меньше 14!");
        }
        if (partNumber.length() > 79) {
            throw new RuntimeException("Длина строки  partnumber должна быть больше 79!");
        }
        this.partNumber = partNumber;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        if (owner == null) {
            throw new RuntimeException("Поле Owner не должно быть null!");
        }
        this.owner = owner;
    }

    public Product(String name, Coordinates coordinates, long price,
                   String partNumber, UnitOfMeasure unitOfMeasure, Person owner) {
        this.id = ++lastId;
        this.creationDate = new Date();
        setName(name);
        setCoordinates(coordinates);
        setPrice(price);
        setPartNumber(partNumber);
        setUnitOfMeasure(unitOfMeasure);
        setOwner(owner);
    }

    public Product(int id,
               String name,
               Coordinates coordinates,
               java.util.Date creationDate,
               long price,
               String partNumber,
               UnitOfMeasure unitOfMeasure,
               Person owner) {

        if (id <= 0) {
            throw new RuntimeException("поле id должно быть больше 0!");
        }
        this.id = id;
        if (id > lastId){
            lastId = id;
        }
        if (creationDate == null) {
            throw new RuntimeException("поле creationDate не может быть null!");
        }
        this.creationDate= creationDate;

        setName(name);
        setCoordinates(coordinates);
        setPrice(price);
        setPartNumber(partNumber);
        setUnitOfMeasure(unitOfMeasure);
        setOwner(owner);
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", price=" + price +
                ", partNumber='" + partNumber + '\'' +
                ", unitOfMeasure=" + unitOfMeasure +
                ", owner=" + owner +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                price == product.price &&
                Objects.equals(name, product.name) &&
                Objects.equals(coordinates, product.coordinates) &&
                Objects.equals(creationDate, product.creationDate) &&
                Objects.equals(partNumber, product.partNumber) &&
                unitOfMeasure == product.unitOfMeasure &&
                Objects.equals(owner, product.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, price, partNumber, unitOfMeasure, owner);
    }

    @Override
    public int compareTo(Product other){
        return Long.compare(this.price, other.price);
    }
}
