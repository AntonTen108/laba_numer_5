package models;


import exceptions.*;

import java.util.Objects;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.time.LocalDateTime birthday; //Поле не может быть null
    private Color eyeColor; //Поле не может быть null
    private Location location; //Поле может быть null

    public void setName(String name) {
        if (name == null) {
            throw new ValidationException("Поле name не может быть null!");
        }
        if (name.isEmpty()) {
            throw new ValidationException("Поле name не может быть пустым!");
        }
        this.name = name;
    }

    public void setBirthday(java.time.LocalDateTime birthday) {
        if (birthday == null) {
            throw new  ValidationException("Поле birthday не может быть null!");
        }
        this.birthday = birthday;
    }

    public void setEyeColor(Color eyeColor) {
        if (eyeColor == null) {
            throw new ValidationException("Поле eyeColor не может быть null!");
        }
        this.eyeColor = eyeColor;
    }

    public void setLocation (Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public java.time.LocalDateTime getBirthday() {
        return birthday;
    }

    public Color getEyeColor() {
        return eyeColor;
    }

    public Location getLocation() {
        return location;
    }

    public Person (String name, java.time.LocalDateTime birthday, Color eyeColor, Location location) {
        setName(name);
        setBirthday(birthday);
        setEyeColor(eyeColor);
        setLocation(location);
    }

    @Override
    public String toString() {
        return "{name = " + name + ", birthday = " + birthday + ", eyeColor = " + eyeColor + ", location = " + location + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person that = (Person) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(birthday, that.birthday) &&
                Objects.equals(eyeColor, that.eyeColor) &&
                Objects.equals(location, that.location);

    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthday, eyeColor, location);
    }
}