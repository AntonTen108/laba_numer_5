package models;

import java.util.Objects;

public class Location {
    private long x;
    private int y;
    private float z;

    public void setX(long x) {
        this.x = x;
    }
    public void setY (int y) {
        this.y = y;
    }
    public void setZ (float z) {
        this.z = z;
    }

    public long getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public float getZ() {
        return z;
    }

    public Location(long x, int y, float z) {
        setX(x);
        setY(y);
        setZ(z);
    }
    @Override
    public String toString() {
        return "{x = " + x + ", y = " + y +", z = " + z + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location that = (Location) o;
        return x == that.x && y == that.y && z == that.z; // доработать z
    }

    @Override
    public int hashCode() {
        return Objects.hash(x,y,z);
    }
}
