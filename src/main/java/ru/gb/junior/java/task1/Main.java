package ru.gb.junior.java.task1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Main {
    public static void main(String[] args) {
        UserData userData = new UserData("John", 19, "123456");

        try (FileOutputStream fileOut = new FileOutputStream("userdata.bin")) {
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(userData);
            System.out.println("Serialization is done!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
