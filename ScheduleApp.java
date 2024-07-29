/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package scheduleapp;

/**
 *
 * @author prade
 */
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

// Task Class
class Task {
    private String description;
    private LocalTime startTime;
    private LocalTime endTime;
    private String priority;

    public Task(String description, String startTime, String endTime, String priority) {
        this.description = description;
        this.startTime = LocalTime.parse(startTime);
        this.endTime = LocalTime.parse(endTime);
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return String.format("%s - %s: %s [%s]", startTime, endTime, description, priority);
    }
}

// TaskFactory Class
class TaskFactory {
    public static Task createTask(String description, String startTime, String endTime, String priority) {
        return new Task(description, startTime, endTime, priority);
    }
}

// ScheduleManager Class
class ScheduleManager {
    private static ScheduleManager instance;
    private List<Task> tasks;

    private ScheduleManager() {
        tasks = new ArrayList<>();
    }

    public static ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }

    public String addTask(String description, String startTime, String endTime, String priority) {
        if (!validateTimeFormat(startTime) || !validateTimeFormat(endTime)) {
            return "Error: Invalid time format.";
        }

        Task newTask = TaskFactory.createTask(description, startTime, endTime, priority);

        for (Task task : tasks) {
            if (newTask.getStartTime().isBefore(task.getEndTime()) && newTask.getEndTime().isAfter(task.getStartTime())) {
                return "Error: Task conflicts with existing task \"" + task.getDescription() + "\".";
            }
        }

        tasks.add(newTask);
        Collections.sort(tasks, Comparator.comparing(Task::getStartTime));
        return "Task added successfully. No conflicts.";
    }

    public String removeTask(String description) {
        for (Task task : tasks) {
            if (task.getDescription().equals(description)) {
                tasks.remove(task);
                return "Task removed successfully.";
            }
        }
        return "Error: Task not found.";
    }

    public String viewTasks() {
        if (tasks.isEmpty()) {
            return "No tasks scheduled for the day.";
        }

        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(task).append("\n");
        }
        return sb.toString();
    }

    private boolean validateTimeFormat(String timeStr) {
        try {
            LocalTime.parse(timeStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

// Main Class
public class ScheduleApp {
    public static void main(String[] args) {
        ScheduleManager manager = ScheduleManager.getInstance();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. View Tasks");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter start time (HH:MM): ");
                    String startTime = scanner.nextLine();
                    System.out.print("Enter end time (HH:MM): ");
                    String endTime = scanner.nextLine();
                    System.out.print("Enter priority (Low/Medium/High): ");
                    String priority = scanner.nextLine();
                    System.out.println(manager.addTask(description, startTime, endTime, priority));
                    break;

                case 2:
                    System.out.print("Enter task description to remove: ");
                    description = scanner.nextLine();
                    System.out.println(manager.removeTask(description));
                    break;

                case 3:
                    System.out.println(manager.viewTasks());
                    break;

                case 4:
                    running = false;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }

        scanner.close();
    }
}
