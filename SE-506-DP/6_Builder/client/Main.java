package client;

import builders.ChickenSandwichBuilder;
import builders.EggSandwichBuilder;
import director.Director;
import products.ChickenSandwich;
import products.EggSandwich;

public class Main {
    public static void main(String[] args) {
        Director director = new Director();

        // Build predefined sandwiches
        ChickenSandwich chickenSandwich = director.makeChickenSandwich(new ChickenSandwichBuilder());
        EggSandwich eggSandwich = director.makeEggSandwich(new EggSandwichBuilder());

        // Display the full menu
        System.out.println("Full Menu:");
        System.out.println("1. " + chickenSandwich);
        System.out.println("2. " + eggSandwich);

        // Build a custom chicken sandwich
        ChickenSandwich customChickenSandwich = director.makeCustomChickenSandwich(
                new ChickenSandwichBuilder(), "Whole Wheat Bread", "Spicy Grilled Chicken", "Honey Mustard"
        );

        System.out.println("\nCustom Order:");
        System.out.println(customChickenSandwich);
    }
}
