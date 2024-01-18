package ru.gb.junior.java.task2;

import ru.gb.junior.java.task1.UserData;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Main {
    public static void main(String[] args) {
        try (FileInputStream fileInput = new FileInputStream("userdata.bin")) {
            ObjectInputStream in = new ObjectInputStream(fileInput);
            UserData userData = (UserData) in.readObject();
            System.out.println("Deserialization is done!");
            System.out.println(userData);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
