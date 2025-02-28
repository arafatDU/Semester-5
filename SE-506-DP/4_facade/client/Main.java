package client;

import pizza.PizzaOrderFacade;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PizzaOrderFacade pizzaOrderFacade = new PizzaOrderFacade();

        // Client inputs essential details only
        String cardNumber = "1234567890";
        String pin = "1234";
        double amount = 25.0;


//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter card number: ");
//        String cardNumber = scanner.nextLine();
//        System.out.print("Enter PIN: ");
//        String pin = scanner.nextLine();
//        System.out.print("Enter amount: ");
//        double amount = scanner.nextDouble();
//        scanner.nextLine();
//        System.out.print("Enter operation type (e.g., 'Order Pizza'): ");
//        String operationType = scanner.nextLine();

        pizzaOrderFacade.orderPizza(cardNumber, pin, amount);
    }
}
