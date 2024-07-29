/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package structural2;

/**
 *
 * @author prade
 */
import java.util.Scanner;

// Component
interface Coffee {
    String getDescription();
    double cost();
}

// ConcreteComponent
class BasicCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Basic Coffee";
    }

    @Override
    public double cost() {
        return 5.00;
    }
}

// Decorator
abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    public abstract String getDescription();
}

// ConcreteDecorator1
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Milk";
    }

    @Override
    public double cost() {
        return coffee.cost() + 1.00;
    }
}

// ConcreteDecorator2
class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Sugar";
    }

    @Override
    public double cost() {
        return coffee.cost() + 0.50;
    }
}

// Main
public class Structural2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Coffee coffee = new BasicCoffee();
        System.out.println("Basic Coffee Cost: $" + coffee.cost());

        System.out.println("Do you want to add milk? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            coffee = new MilkDecorator(coffee);
        }

        System.out.println("Do you want to add sugar? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            coffee = new SugarDecorator(coffee);
        }

        System.out.println(coffee.getDescription() + " Cost: $" + coffee.cost());
        scanner.close();
    }
}

