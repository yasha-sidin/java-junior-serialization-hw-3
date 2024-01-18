package ru.gb.junior.java.task3;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToDoListApp {
    public static final String FILE_JSON = "tasks.json";
    public static final String FILE_BIN = "tasks.bin";
    public static final String FILE_XML = "tasks.xml";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final XmlMapper xmlMapper = new XmlMapper();

    public static void addNewTask(Scanner scanner, List<ToDoV2> tasks) {
        System.out.print("Input new task's name: ");
        String newTaskTitle = scanner.nextLine();
        tasks.add(new ToDoV2(newTaskTitle));
        saveTasksToFile(FILE_JSON, tasks);
        saveTasksToFile(FILE_BIN, tasks);
        saveTasksToFile(FILE_XML, tasks);
        System.out.println("New task was added successfully!");
    }

    public static void markTaskAsDone(Scanner scanner, List<ToDoV2> tasks) {
        System.out.print("Input number of task which you want to mark as done: ");
        String input = scanner.nextLine();
        try {
            int taskNumber = Integer.parseInt(input) - 1;
            if (taskNumber >= 0 && taskNumber < tasks.size()) {
                if (!tasks.get(taskNumber).isDone()) {
                    tasks.get(taskNumber).setDone(true);
                    saveTasksToFile(FILE_JSON, tasks);
                    saveTasksToFile(FILE_BIN, tasks);
                    saveTasksToFile(FILE_XML, tasks);
                    System.out.println("Task " +  (taskNumber + 1) + " was updated successfully!");
                } else {
                    System.out.println("Task " + (taskNumber + 1) + " has already done");
                }
            } else {
                System.out.println("Not a valid number of task. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Not a valid input. Please try again.");
        }
    }

    public static List<ToDoV2> loadTasksFromFile(String fileName) {
        List<ToDoV2> tasks = new ArrayList<>();
        File file = new File(fileName);
        if (file.exists()) {
            try {
                if (fileName.endsWith(".json")) {
                    tasks = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, ToDoV2.class));
                } else if (fileName.endsWith(".bin")) {
                    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                        tasks = (List<ToDoV2>) in.readObject();
                    }
                } else if (fileName.endsWith(".xml")) {
                    tasks = xmlMapper.readValue(file, xmlMapper.getTypeFactory().constructCollectionType(List.class, ToDoV2.class));
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        return tasks;
    }

    public static void saveTasksToFile(String fileName, List<ToDoV2> tasks) {
        try {
            if (fileName.endsWith(".json")) {
                objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                objectMapper.writeValue(new File(fileName), tasks);
            } else if (fileName.endsWith(".bin")) {
                try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
                    out.writeObject(tasks);
                }
            } else if (fileName.endsWith(".xml")) {
//                String s = xmlMapper.writeValueAsString(tasks);
//                System.out.println(s);
                xmlMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                xmlMapper.writeValue(new File(fileName), tasks);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
