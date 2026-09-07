package models;

import java.io.Serializable;
import java.util.Objects;

public class Coordinates implements Serializable {
    private Double x; //Поле не может быть null
    private Integer y; //Поле не может быть null

    public Double getX() {
        return x;
    }
    public Integer getY() {
        return y;
    }

    public void setX(Double x) {
        if (x == null) {
            throw new RuntimeException("x не может быть null!");
        }
        this.x = x;
    }
    public void setY(Integer y) {
        if (y == null) {
            throw new RuntimeException("y не может быть null!");
        }
            this.y = y;
    }

    @Override
    public String toString() {
        return "{x = " + x + " y = " + y + "}";
    }

    public Coordinates(Double x, Integer y) {
        setX(x);
        setY(y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null ||  getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Objects.equals(x, that.x) && Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x,y);
    }
}

