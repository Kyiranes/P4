import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static Scanner input = new Scanner(System.in);
    static ArrayList<TaskName> tasks = new ArrayList<>();

    public static void main(String[] args) {
        deserialization();
        boolean end = false;
        ArrayList<String> menu = new ArrayList<>();
        menu.add("(0) Add a task.");
        menu.add("(1) Remove a task.");
        menu.add("(2) Update a task.");
        menu.add("(3) List all tasks.");
        menu.add("(4) List all tasks of a certain priority.");
        menu.add("(5) End Process");
        while (!end) {
            System.out.println(menu);
            int selection = input.nextInt();
            input.nextLine();
            switch (selection) {
                case 0 -> addTask();
                case 1 -> removeTask();
                case 2 -> updateTask();
                case 3 -> listTask();
                case 4 -> listTaskPriority();
                case 5 -> {
                    end = true;
                    serialization(tasks);
                }
                default -> System.out.println("Invalid input");
            }

        }
    }

    private static void listTask() {
        Collections.sort(tasks);
        if (!tasks.isEmpty())
            tasks.forEach(System.out::println);
        else
            System.out.println("No tasks.");
    }

    private static void listTaskPriority() {
        System.out.println("What is the priority of the tasks you want to list?");
        int priority = input.nextInt();
        Collections.sort(tasks);
        tasks.forEach(task -> {
            if (task.getPriority() == priority) {
                System.out.println(task);
            }
        });

    }

    private static void updateTask() {
        TaskName task = new TaskName();
        System.out.println(tasks);
        System.out.println("Enter the index of the task to update: ");
        int index = input.nextInt();
        System.out.println("Enter the name, description, and priority(0-5) of the task");
        input.nextLine();
        String name = input.nextLine();
        String description = input.nextLine();
        int priority = input.nextInt();
        input.nextLine();
        task.setName(name);
        task.setDescription(description);
        task.setPriority(priority);
        tasks.set(index, task);
        Collections.sort(tasks);
        tasks.forEach(System.out::println);

    }

    private static void removeTask() {

        tasks.forEach(System.out::println);
        System.out.println("Enter the index of the task to remove: ");
        tasks.remove(input.nextInt());
        input.nextLine();
        Collections.sort(tasks);
        tasks.forEach(System.out::println);


    }

    public static void addTask() {
        TaskName task = new TaskName();
        System.out.println("Enter the name, description, and priority(0-5) of the task");
        String name = input.nextLine();
        String description = input.nextLine();
        int priority = input.nextInt();
        task.setName(name);
        task.setDescription(description);
        task.setPriority(priority);
        tasks.add(task);
        Collections.sort(tasks);
        tasks.forEach(System.out::println);
    }


    static void deserialization(){
        try(FileReader reader = new FileReader("data.json")){
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(reader);
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<TaskName>>(){}.getType();
            tasks = gson.fromJson(element, type);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    static void serialization(ArrayList<TaskName> tasks) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter("data.json")) {
            gson.toJson(tasks, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
