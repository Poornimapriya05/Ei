/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package structural1;

/**
 *
 * @author prade
 */
import java.util.Scanner;

// Target
interface NewPaymentSystem {
    void makePayment(int amount);
}

// Adaptee
class LegacyPaymentSystem {
    public void processPayment(int amount) {
        System.out.println("Processing payment of " + amount + " using Legacy System.");
    }
}

// Adapter
class PaymentAdapter implements NewPaymentSystem {
    private LegacyPaymentSystem legacySystem;

    public PaymentAdapter(LegacyPaymentSystem legacySystem) {
        this.legacySystem = legacySystem;
    }

    @Override
    public void makePayment(int amount) {
        legacySystem.processPayment(amount);
    }
}

// Main
public class Structural1 {
    public static void main(String[] args) {
        LegacyPaymentSystem legacySystem = new LegacyPaymentSystem();
        NewPaymentSystem newPaymentSystem = new PaymentAdapter(legacySystem);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter amount to pay: ");
        int amount = scanner.nextInt();

        newPaymentSystem.makePayment(amount);
        scanner.close();
    }
}

