package commands;

import models.Product;
import network.Response;

import java.util.List;

public abstract class AbstractCommand implements Command {

    protected int parseId(String value) {
        final int id;

        try {
            id = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Некорректный id: " + value
            );
        }

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "Значение id должно быть больше нуля!"
            );
        }

        return id;
    }

    protected void requireArgs(String[] args, int count) {
        if (args == null || args.length < count) {
            throw new IllegalArgumentException(
                    "Недостаточно аргументов для команды " + getName()
            );
        }
    }

    protected Response productList(
            List<Product> products,
            String emptyMessage
    ) {
        if (products == null || products.isEmpty()) {
            return Response.ok(emptyMessage);
        }

        return Response.ok("", products);
    }
}