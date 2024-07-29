/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package behaviour1;

/**
 *
 * @author prade
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Subject
interface WeatherSubject {
    void addObserver(WeatherObserver observer);
    void removeObserver(WeatherObserver observer);
    void notifyObservers();
}

// ConcreteSubject
class WeatherStation implements WeatherSubject {
    private List<WeatherObserver> observers = new ArrayList<>();
    private float temperature;

    public void setTemperature(float temperature) {
        this.temperature = temperature;
        notifyObservers();
    }

    @Override
    public void addObserver(WeatherObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(WeatherObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (WeatherObserver observer : observers) {
            observer.update(temperature);
        }
    }
}

// Observer
interface WeatherObserver {
    void update(float temperature);
}

// ConcreteObserver
class TemperatureDisplay implements WeatherObserver {
    private float temperature;

    @Override
    public void update(float temperature) {
        this.temperature = temperature;
        display();
    }

    public void display() {
        System.out.println("Temperature Display: " + temperature + "°C");
    }
}

// Main
public class Behaviour1 {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();
        TemperatureDisplay tempDisplay = new TemperatureDisplay();

        weatherStation.addObserver(tempDisplay);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter new temperature (or type 'exit' to quit): ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            try {
                float temperature = Float.parseFloat(input);
                weatherStation.setTemperature(temperature);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
        scanner.close();
    }
}
