package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InteractiveInput implements InputSource {

    private BufferedReader reader;

    public InteractiveInput (){
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public String nextLine() {
        try {
           return reader.readLine();
        } catch (IOException e){
        throw new RuntimeException("Ошибка чтения введенного текста! " +e.getMessage());
        }
    }

    @Override
    public void clue(String fieldName) {
     System.out.println("Введите " + fieldName + " : ");
    }
}
