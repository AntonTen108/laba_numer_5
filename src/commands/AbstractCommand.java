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
    /*
     все методы имеют модификатор доступа protected, так как они используются только в рамках пакета commands

     класс написан для того чтобы, следовать принципу Don't repeat you're self.
     каждый его метод используется в нескольких командах.


     метод parseId - используется в командах: updateId, removeKey, replace_if_greater, replace_if_lower.
     метод получает на вход строку с текстом и парсит ее в числовое значение.


     метод createAsker - используется в командах: insert, updateId, remove_lower, replace_if_greater, replace_if_lower, filter_greater_than_owner.
     метод получает объект типа интерфейса InputSource
     оператор instanceof проверяет фактический класс объекта
     если создан через InteractiveInput - вернет true
     если создан через ScriptInput - вернет false
     далее создается новый объект ProductAsker, в дальнейшем это определяет как программа будет реагировать на некорректный ввод


     метод requireArgs - используется в командах: update_id, remove_key, Replace_if_greater, replace_if_lower, Filter_contains_name, execute_script
     метод получает массив args и число count - сколько аргументов требуется для корректной работы команды
     args.lenght - возвращает число = текущий размер массива, если это числа меньше args кидается exception.
     метод void т.к он используется как проверка, при не выполнении throw RunTimeException

     метод productList - используется в командах: show, filter_contains_name, filter_greater_than_owner.
     метод получает коллекцию Product, если она не пуста то происходит перебор каждого элемента через for-each.
     затем каждый ToString элемента добавляется в строку res, она же и возвращается.
    */
}
