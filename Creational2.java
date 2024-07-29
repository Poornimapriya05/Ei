/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package creational2;

/**
 *
 * @author prade
 */
import java.util.Scanner;

// Product
interface Vehicle {
    void drive();
}

// ConcreteProduct1
class Car implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Driving a Car.");
    }
}

// ConcreteProduct2
class Bike implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Riding a Bike.");
    }
}

// Creator
abstract class VehicleFactory {
    public abstract Vehicle createVehicle();
}

// ConcreteCreator1
class CarFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new Car();
    }
}

// ConcreteCreator2
class BikeFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new Bike();
    }
}

// Main
public class Creational2{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter vehicle type (car/bike): ");
        String vehicleType = scanner.nextLine();

        VehicleFactory factory;
        if (vehicleType.equalsIgnoreCase("car")) {
            factory = new CarFactory();
        } else if (vehicleType.equalsIgnoreCase("bike")) {
            factory = new BikeFactory();
        } else {
            System.out.println("Invalid vehicle type.");
            scanner.close();
            return;
        }

        Vehicle vehicle = factory.createVehicle();
        vehicle.drive();
        scanner.close();
    }
}

