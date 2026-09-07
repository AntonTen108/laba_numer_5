package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * класс для чтения данных из консоли
 * реализует интерфейс InputSource
 * использует BufferedReader, чтобы считывать строки, которые вводит пользователь
 */
public class InteractiveInput implements InputSource {
    /**
     * объект для чтения текста из консоли
     */
    private BufferedReader reader;
    /**
     * конструктор BufferedReader который читает данные из System.in
     */
    public InteractiveInput (){
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }
    /**
     * считывает одну строку которую ввёл пользователь
     * @return строка введённая пользователем
     * @throws RuntimeException если произошла ошибка при чтении из консоли
     */
    @Override
    public String nextLine() {
        try {
           return reader.readLine();
        } catch (IOException e){
        throw new RuntimeException("Ошибка чтения введенного текста! " +e.getMessage());
        }
    }
    /**
     * выводит в консоль подсказку для ввода поля
     * @param fieldName название поля которое должен ввести пользователь
     */
    @Override
    public void clue(String fieldName) {
     System.out.println("Введите " + fieldName + " : ");
    }
}
/*
класс читает данные из консоли от пользователя

метод nextLine читает следующую строку

метод clue выводит подсказку для пользователя перед тем как он напишет какие либо данные
 */