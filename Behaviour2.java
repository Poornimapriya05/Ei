/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package behaviour2;

/**
 *
 * @author prade
 */
import java.util.Scanner;

// Strategy
interface PaymentStrategy {
    void pay(int amount);
}

// ConcreteStrategy1
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card.");
    }
}

// ConcreteStrategy2
class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using PayPal.");
    }
}

// Context
class PaymentContext {
    private PaymentStrategy strategy;

    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void executePayment(int amount) {
        strategy.pay(amount);
    }
}

// Main
public class Behaviour2 {
    public static void main(String[] args) {
        PaymentContext paymentContext = new PaymentContext();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter amount to pay: ");
        int amount = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("Choose payment method (credit/paypal): ");
        String paymentMethod = scanner.nextLine();

        if (paymentMethod.equalsIgnoreCase("credit")) {
            System.out.println("Enter credit card number: ");
            String cardNumber = scanner.nextLine();
            paymentContext.setPaymentStrategy(new CreditCardPayment(cardNumber));
        } else if (paymentMethod.equalsIgnoreCase("paypal")) {
            System.out.println("Enter PayPal email: ");
            String email = scanner.nextLine();
            paymentContext.setPaymentStrategy(new PayPalPayment(email));
        } else {
            System.out.println("Invalid payment method.");
            scanner.close();
            return;
        }

        paymentContext.executePayment(amount);
        scanner.close();
    }
}

