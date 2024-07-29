/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package creational1;

/**
 *
 * @author prade
 */
import java.util.Scanner;

// Singleton
class DatabaseConnectionManager {
    private static DatabaseConnectionManager instance;

    private DatabaseConnectionManager() {}

    public static synchronized DatabaseConnectionManager getInstance() {
        if (instance == null) {
            instance = new DatabaseConnectionManager();
        }
        return instance;
    }

    public void connect() {
        System.out.println("Connecting to the database...");
    }
}

// Main
public class Creational1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Type 'connect' to connect to the database or 'exit' to quit:");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                break;
            } else if (input.equalsIgnoreCase("connect")) {
                DatabaseConnectionManager dbManager = DatabaseConnectionManager.getInstance();
                dbManager.connect();
            } else {
                System.out.println("Invalid command.");
            }
        }
        scanner.close();
    }
}
