package ru.gb.junior.java.task3;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ru.gb.junior.java.task3.ToDoListApp.*;

public class Program {
    public static void main(String[] args) {
        List<ToDoV2> tasks;
        File f = new File(FILE_JSON);
        if (f.exists() && !f.isDirectory()) {
            tasks = ToDoListApp.loadTasksFromFile(FILE_JSON);
        } else {
            tasks = prepareTasks();
            ToDoListApp.saveTasksToFile(FILE_JSON, tasks);
            ToDoListApp.saveTasksToFile(FILE_BIN, tasks);
            ToDoListApp.saveTasksToFile(FILE_XML, tasks);
        }
        System.out.print(tasksToString(tasks));
        Scanner scanner = new Scanner(System.in);
        while (true) {
            menu();
            String commandS = scanner.nextLine();
            try {
                int command = Integer.parseInt(commandS);
                switch (command) {
                    case 1: {
                        ToDoListApp.addNewTask(scanner, tasks);
                        System.out.print(tasksToString(tasks));
                        break;
                    }
                    case 2: {
                        ToDoListApp.markTaskAsDone(scanner, tasks);
                        System.out.print(tasksToString(tasks));
                        break;
                    }
                    case 3: {
                        System.out.println("Exit!");
                        return;
                    }
                    default: {
                        System.out.println("Wrong command. Try again.");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid format. Try again.");
            }
        }
    }

    public static void menu() {
        System.out.println("\tMenu: ");
        System.out.println("1. Add new task");
        System.out.println("2. Mark a task");
        System.out.println("3. Exit");
        System.out.print("Choose command: ");
    }

    private static List<ToDoV2> prepareTasks() {
        ArrayList<ToDoV2> list = new ArrayList<>();
        list.add(new ToDoV2("Go to the product market"));
        list.add(new ToDoV2("Walk with a dog"));
        list.add(new ToDoV2("Change a lamp"));
        return list;
    }

    private static String tasksToString(List<ToDoV2> tasks) {
        StringBuilder sb = new StringBuilder("List of tasks: ");
        sb.append('\n');
        int index = 0;
        for (ToDoV2 task : tasks) {
            sb.append(++index).append(' ').append('[').append(task.isDone() ? (char) 10003 : " ").append(']').append(" ").append(task.getTitle()).append('\n');
        }
        return sb.toString();
    }
}
