package ru.gb.junior.java.hw.task1;

import java.util.List;

public class Program {
    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student("John", 19, 4.4),
                new Student("Ella", 20, 2.9),
                new Student("Mark", 18, 4.0)
        );
        ObjectCollectionController<Student> objectCollectionController = new ObjectCollectionController<>("students.json", "students.bin", "student.xml");

        System.out.println("\tSTARTING COLLECTION");
        System.out.println(students + "\n");

        System.out.println("\tWITH JSON");
        objectCollectionController.saveToJson(students);
        System.out.println("Saving to json file is completed!");
        System.out.println("Loading from json file...");
        System.out.println(objectCollectionController.loadFromJson(Student.class));
        System.out.println("Loading from json file is done!\n");

        System.out.println("\tWITH BIN");
        objectCollectionController.saveToBin(students);
        System.out.println("Saving to bin file is completed!");
        System.out.println("Loading from bin file...");
        System.out.println(objectCollectionController.loadFromBin(Student.class));
        System.out.println("Loading from bin file is done!\n");

        System.out.println("\tWITH XML");
        objectCollectionController.saveToXml(students);
        System.out.println("Saving to xml file is completed!");
        System.out.println("Loading from xml file...");
        System.out.println(objectCollectionController.loadFromXml(Student.class));
        System.out.println("Loading from xml file is done!");
    }
}
