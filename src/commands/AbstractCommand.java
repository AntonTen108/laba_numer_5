package commands;

import io.InputSource;
import io.InteractiveInput;
import io.ProductAsker;
import models.Product;

import java.util.List;

public abstract class AbstractCommand  implements Command {

    protected int parseId(String args) {
        try {
            return Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Некорректный id = " + args);
        }
    }

    protected ProductAsker createAsker(InputSource source) {
        boolean allowRetry = source instanceof InteractiveInput;
        return new ProductAsker(allowRetry);
    }

    protected void requireArgs(String[] args, int count) {
        if (args.length < count) {
            throw new RuntimeException(" Недостаточно аргументов для команды" + getName());
        }
    }

    protected String productList(List<Product> products, String empty) {
        if (products.isEmpty()) {
            return empty;
        }
        String res = "";
        for (Product p : products) {
            res += p.toString() + "\n";
        }
        return res;
    }
}
