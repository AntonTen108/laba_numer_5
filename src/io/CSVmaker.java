package io;

import exceptions.CsvParseException;
import models.*;

import java.time.LocalDateTime;
import java.util.Date;

public class CSVmaker {

    public String toCSV(Product p) {
        Location location = p.getOwner().getLocation();
        String partLocation = (location == null) ? "||" : (location.getX() + "|" + location.getY() + "|" + location.getZ());
        UnitOfMeasure unitOfMeasure = p.getUnitOfMeasure();
        String nullUnitOfMeasure = (unitOfMeasure == null) ? "" :(unitOfMeasure.name());
        String res = p.getId() + "|" +
                p.getName() + "|" +
                p.getCoordinates().getX() + "|" +
                p. getCoordinates().getY() + "|" +
                p.getCreationDate().getTime() + "|" +
                p.getPrice() + "|" +
                p.getPartNumber() + "|" +
                nullUnitOfMeasure +"|" +
                p.getOwner().getName() + "|" +
                p.getOwner().getBirthday() + "|" +
                p.getOwner().getEyeColor() + "|" +
                partLocation + "|";
        return res;
    }

    public Product fromCsv (String line) {
       try {


           String[] parts = line.split("\\|");

           int id = Integer.parseInt(parts[0]);
           String name = parts[1];
           double x = Double.parseDouble(parts[2]);
           int y = Integer.parseInt(parts[3]);
           Date creationDate = new Date(Long.parseLong(parts[4]));
           long price = Long.parseLong(parts[5]);
           String partNumber = parts[6];
           String unitPart = parts[7];
           UnitOfMeasure unitOfMeasure = unitPart.isEmpty() ? null : UnitOfMeasure.valueOf(unitPart);
           String nameOwner = parts[8];
           LocalDateTime birthday = LocalDateTime.parse(parts[9]);
           Color eyeColor = Color.valueOf(parts[10]);
           Location location;
           if (parts[11].isEmpty()) {
               location = null;
           } else {
               long locX = Long.parseLong(parts[11]);
               int locY = Integer.parseInt(parts[12]);
               float locZ = Float.parseFloat(parts[13]);
               location = new Location(locX, locY, locZ);
           }
           Coordinates coordinates = new Coordinates(x, y);
           Person owner = new Person(nameOwner, birthday, eyeColor, location);

           return new Product(id, name, coordinates, creationDate, price, partNumber, unitOfMeasure, owner);
       } catch (Exception e) {
           throw new CsvParseException("Не удалось обработать строку" + line + e);
       }

    }
}
