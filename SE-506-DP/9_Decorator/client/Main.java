package client;

import coffee.Coffee;
import coffee.PlainCoffee;
import decorators.MilkDecorator;
import decorators.SugarDecorator;
import decorators.WhippedCreamDecorator;

public class Main {
    public static void main(String[] args) {

        Coffee coffee = new PlainCoffee();
        System.out.println(coffee.getDescription() + " -> $" + coffee.getCost());

        // Add milk
        coffee = new MilkDecorator(coffee);
        //System.out.println(coffee.getDescription() + " -> $" + coffee.getCost());

        // Add sugar
        coffee = new SugarDecorator(coffee);
        //System.out.println(coffee.getDescription() + " -> $" + coffee.getCost());

        // Add whipped cream
        coffee = new WhippedCreamDecorator(coffee);
        System.out.println(coffee.getDescription() + " -> $" + coffee.getCost());
    }
}
