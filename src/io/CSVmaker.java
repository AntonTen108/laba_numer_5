package io;

import models.Color;
import models.Coordinates;
import models.Location;
import models.Person;
import models.Product;
import models.UnitOfMeasure;

import java.time.LocalDateTime;
import java.util.Date;

public class CSVmaker {

    public String toCSV(Product product) {
        Location location = product.getOwner().getLocation();

        String locationX = location == null ? "" : String.valueOf(location.getX());

        String locationY = location == null ? "" : String.valueOf(location.getY());

        String locationZ = location == null ? "" : String.valueOf(location.getZ());

        String unitOfMeasure = product.getUnitOfMeasure() == null ? "" : product.getUnitOfMeasure().name();

        return String.join("|", String.valueOf(product.getId()),
                product.getName(),
                String.valueOf(product.getCoordinates().getX()),
                String.valueOf(product.getCoordinates().getY()),
                String.valueOf(product.getCreationDate().getTime()),
                String.valueOf(product.getPrice()),
                product.getPartNumber(),
                unitOfMeasure,
                product.getOwner().getName(),
                product.getOwner().getBirthday().toString(),
                product.getOwner().getEyeColor().name(),
                locationX,
                locationY,
                locationZ
        );
    }

    public Product fromCsv(String line) {
        try {
            String[] parts = line.split("\\|", -1);

            if (parts.length < 14) {
                throw new IllegalArgumentException("Недостаточно полей в строке");
            }

            int id = Integer.parseInt(parts[0]);
            String name = parts[1];

            double coordinateX = Double.parseDouble(parts[2]);
            int coordinateY = Integer.parseInt(parts[3]);

            Date creationDate = new Date(Long.parseLong(parts[4]));

            long price = Long.parseLong(parts[5]);
            String partNumber = parts[6];

            UnitOfMeasure unitOfMeasure = parts[7].isEmpty() ? null : UnitOfMeasure.valueOf(parts[7]);

            String ownerName = parts[8];
            LocalDateTime birthday = LocalDateTime.parse(parts[9]);
            Color eyeColor = Color.valueOf(parts[10]);

            Location location = null;

            if (!parts[11].isEmpty()
                    || !parts[12].isEmpty()
                    || !parts[13].isEmpty()) {
                long locationX = Long.parseLong(parts[11]);
                int locationY = Integer.parseInt(parts[12]);
                float locationZ = Float.parseFloat(parts[13]);

                location = new Location(locationX, locationY, locationZ);
            }

            Coordinates coordinates = new Coordinates(coordinateX, coordinateY);

            Person owner = new Person(ownerName, birthday, eyeColor, location);

            return new Product(id, name, coordinates, creationDate, price, partNumber, unitOfMeasure, owner);
        } catch (RuntimeException e) {throw new RuntimeException("Не удалось обработать строку: " + line + ". Причина: " + e.getMessage(), e);
        }
    }
}