package io;

public interface InputSource {
     String nextLine ();
     void  clue(String fieldNAme);

}
/*
этот интерфейс задает общий способ получения информации и выводы подсказок

метод nextLine нужен для того, чтобы возвращать следующую строку ввода

метод clue нужен для вывода подсказок
 */