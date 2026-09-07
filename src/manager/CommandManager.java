package manager;

import collection.CollectionManager;
import commands.Command;
import models.Coordinates;
import models.Person;
import models.Product;
import network.Request;
import network.Response;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class CommandManager {
    private final Map<String, Command> commands = new LinkedHashMap<>();

    public void register(Command command) {
        commands.put(
                command.getName().toLowerCase(Locale.ROOT),
                command
        );
    }

    public Collection<Command> getCommands() {
        return commands.values();
    }

    public Response handle(Request request, CollectionManager manager) {
        if (request == null) {
            return Response.error("Пустой запрос!");
        }

        String commandName = request.getCommandName();

        if (commandName == null || commandName.isBlank()) {
            return Response.error("Название команды не указано!");
        }

        String name = commandName.trim().toLowerCase(Locale.ROOT);
        Command command = commands.get(name);

        if (command == null) {
            return Response.error("Команда не найдена: " + commandName);
        }

        try {
            validateRequest(request);
            return command.execute(request, manager, this);
        } catch (RuntimeException e) {
            return Response.error("Ошибка: " + e.getMessage());
        }
    }

    private void validateRequest(Request request) {
        Product product = request.getProduct();

        if (product != null) {
            validateProduct(product);
        }

        Person owner = request.getOwner();

        if (owner != null) {
            validatePerson(owner);
        }
    }

    private void validateProduct(Product product) {
        if (product.getName() == null || product.getName().isBlank()) {
            throw new IllegalArgumentException(
                    "Название продукта не может быть пустым!"
            );
        }

        Coordinates coordinates = product.getCoordinates();

        if (coordinates == null) {
            throw new IllegalArgumentException(
                    "Координаты продукта не переданы!"
            );
        }

        if (coordinates.getX() == null || coordinates.getY() == null) {
            throw new IllegalArgumentException(
                    "Значения координат не могут быть null!"
            );
        }

        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException(
                    "Цена продукта должна быть больше нуля!"
            );
        }

        String partNumber = product.getPartNumber();

        if (partNumber == null) {
            throw new IllegalArgumentException(
                    "Поле partNumber не может быть null!"
            );
        }

        if (partNumber.length() < 14 || partNumber.length() > 79) {
            throw new IllegalArgumentException(
                    "Длина partNumber должна быть от 14 до 79 символов!"
            );
        }

        if (product.getOwner() == null) {
            throw new IllegalArgumentException(
                    "Владелец продукта не передан!"
            );
        }

        validatePerson(product.getOwner());
    }

    private void validatePerson(Person person) {
        if (person.getName() == null || person.getName().isBlank()) {
            throw new IllegalArgumentException(
                    "Имя владельца не может быть пустым!"
            );
        }

        if (person.getBirthday() == null) {
            throw new IllegalArgumentException(
                    "Дата рождения владельца не передана!"
            );
        }

        if (person.getEyeColor() == null) {
            throw new IllegalArgumentException(
                    "Цвет глаз владельца не передан!"
            );
        }
    }
}