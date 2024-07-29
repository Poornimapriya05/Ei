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

public class ScheduleManager {
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
}

