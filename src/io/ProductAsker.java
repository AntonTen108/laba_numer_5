package io;
import models.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class ProductAsker {

    private final boolean allowRetry;

    public ProductAsker(boolean allowRetry) {
        this.allowRetry = allowRetry;
    }

    public String askString(InputSource source, String fieldName) {
        source.clue(fieldName);
        return source.nextLine();
    }

    public String askStringIsNotEmpty(InputSource source, String fieldName) {
        while (true) {
            source.clue(fieldName);
            String input = source.nextLine();
             if (!input.isEmpty()) {
                 return input;
             }
             if (!allowRetry) {
                 throw new RuntimeException(" Поле " + fieldName + " не может быть пустым, попробуйте снова!");
             } System.out.println("Вы ввели пустое значение, пожалуйста попробуйте снова");
        }
    }

    public double askDouble(InputSource source, String fieldName) {
        while (true) {
            source.clue(fieldName);
            String input = source.nextLine();
            try {
                double answer = Double.parseDouble(input);
                return answer;
        } catch (NumberFormatException e) {
                if (!allowRetry) {
                throw new RuntimeException("Значение поля " + fieldName+ " введено неверно!");
                } System.out.println("Вы вели некорректный формат значения, пожалуйста попробуйте снова");
            }
            }
        }

        public int askInt (InputSource source, String fieldName) {
            while (true) {
                source.clue(fieldName);
                String input = source.nextLine();
                try {
                    int answer = Integer.parseInt(input);
                    return answer;
                } catch (NumberFormatException e) {
                    if (!allowRetry) {
                        throw new RuntimeException("Значение поля " + fieldName+ " введено неверно!");
                    } System.out.println("Вы вели некорректный формат значения, пожалуйста попробуйте снова");
                }
            }
        }

        public long askLong(InputSource source, String fieldName) {
            while (true) {
                source.clue(fieldName);
                String input = source.nextLine();
                try {
                    long answer = Long.parseLong(input);
                    return answer;
                } catch (NumberFormatException e) {
                    if (!allowRetry) {
                        throw new RuntimeException("Значение поля " + fieldName+ " введено неверно!");
                    } System.out.println("Вы вели некорректный формат значения, пожалуйста попробуйте снова");
                }
            }
        }

        public float askFloat(InputSource source, String fieldName) {
            while (true) {
                source.clue(fieldName);
                String input = source.nextLine();
                try {
                    float answer = Float.parseFloat(input);
                    return answer;
                } catch (NumberFormatException e) {
                    if (!allowRetry) {
                        throw new RuntimeException("Значение поля " + fieldName+ " введено неверно!");
                    } System.out.println("Вы вели некорректный формат значения, пожалуйста попробуйте снова");
                }
            }
        }

    public <T extends Enum<T>> T askEnum(InputSource source, String fieldName, Class<T> enumClass) {
        T[] constants = enumClass.getEnumConstants();
        System.out.println("Доступные значения для ввода " + fieldName + ": " + Arrays.toString(constants));

        while (true) {
            source.clue(fieldName);
            String input = source.nextLine();
            try {
                return Enum.valueOf(enumClass, input.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                if (!allowRetry) {
                    throw new RuntimeException("Значение поля " + fieldName + " введено неверно!");
                }
                System.out.println("Такой константы нет, пожалуйста попробуйте снова");
            }
        }
    }

    public LocalDateTime askDateTime(InputSource source, String fieldName){
        while (true) {
            source.clue(fieldName);
            String input = source.nextLine();
            try {
                LocalDateTime answer = LocalDateTime.parse(input);
                return answer;
            } catch (DateTimeParseException e) {
                if (!allowRetry) {
                    throw new RuntimeException("Значение поля " + fieldName+ " введено неверно!");
                } System.out.println("Вы вели некорректный формат значения, пожалуйста попробуйте снова");
          }
      }
    }

    public Coordinates askCoordinates (InputSource source) {
        double x = askDouble(source, "координату x");
        int y = askInt(source, "координату y");
        return new Coordinates(x, y);
    }

    public Location askLocation(InputSource source) {
        while (true) {
            source.clue(" координату x (нажмите enter, если хотите оставить класс location пустым)");
            String xInput = source.nextLine();

            if (xInput.isEmpty()) {
                return null;
            }

            try {
                long x = Long.parseLong(xInput);
                int y = askInt(source, " координату y ");
                float z = askFloat(source, "координату z ");
                return new Location(x, y, z);
            } catch (NumberFormatException e) {
                if (!allowRetry) {
                    throw new RuntimeException("Введён некорректный тип данных для поля x!");
                }
                System.out.println("Вы ввели некорректный формат значения, пожалуйста попробуйте снова");
            }
        }
    }


    public  Person askPerson(InputSource source) {
        String name = askStringIsNotEmpty(source, "имя владельца");
        LocalDateTime birthday = askDateTime(source, "дату рождения формата(гггг-мм-ддT(англ)чч:мм:сс)");
        Color eyeColor = askEnum(source, "цвет глаз", Color.class);
        Location location = askLocation(source);
        return new Person(name, birthday, eyeColor, location);
    }

    public Product askProduct(InputSource source) {
        String name = askStringIsNotEmpty(source, "название продукта");
        Coordinates coordinates = askCoordinates(source);
        long price = askLong(source, "цену");
        String partNumber = askStringIsNotEmpty(source, "порядковый номер. Он должен составлять не менее 14 символов, но не более 79");
        UnitOfMeasure unitOfMeasure = askEnum(source, "",  UnitOfMeasure.class);
        Person owner = askPerson(source);
        return new Product(name,  coordinates, price, partNumber, unitOfMeasure, owner);

    }
    /*
    этот класс нужен для запроса и проверки данных, необходимых для модели

    поле allowRetry нужно для того, чтобы понять нужно ли повторять ввод при ошибке (чтение в интерактивном режиме или из файла)

    метод askStringIsNotEmpty запрашивает не пустую строку

    методы askDouble, askInt, askLong, askFloat, запрашивают числа

    метод askEnum запрашивает enum, в интерактивном режиме выводит все доступные константы, также реализована "защита" от нижнего регистра и каждый ввод пользователя автоматические превращается в верхний регистр.

    метод askDateTime запрашивает дату и время

    метод askLocation запрашивает координаты, реализована возможность оставить пустым

    метод askPerson запрашивает данные о владельце

    метод askProduct запрашивает данные о продукте
     */
}


