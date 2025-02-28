package client;

import behavior.AggressiveBehavior;
import behavior.Behavior;
import behavior.DefensiveBehavior;
import behavior.NormalBehavior;
import context.RobotContext;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create robots with initial behaviors
        RobotContext robot1 = new RobotContext("Robot1", new AggressiveBehavior());
        RobotContext robot2 = new RobotContext("Robot2", new DefensiveBehavior());
        RobotContext robot3 = new RobotContext("Robot3", new NormalBehavior());

        // Perform initial behaviors
        robot1.performBehavior();
        robot2.performBehavior();
        robot3.performBehavior();

        // Change behaviors dynamically
        System.out.println("\nChanging behaviors dynamically...");
        robot1.setBehavior(new NormalBehavior());
        robot2.setBehavior(new AggressiveBehavior());
        robot3.setBehavior(new DefensiveBehavior());

        // Perform updated behaviors
        robot1.performBehavior();
        robot2.performBehavior();
        robot3.performBehavior();

        // Interactive example
        System.out.println("\nInteractive Example:");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter robot name: ");
        String robotName = scanner.nextLine();

        System.out.println("Choose a behavior: 1. Aggressive  2. Defensive  3. Normal");
        int choice = scanner.nextInt();

        RobotContext customRobot = new RobotContext(robotName, new NormalBehavior());
        switch (choice) {
            case 1 -> customRobot.setBehavior(new AggressiveBehavior());
            case 2 -> customRobot.setBehavior(new DefensiveBehavior());
            case 3 -> customRobot.setBehavior(new NormalBehavior());
            default -> System.out.println("Invalid choice. Keeping normal behavior.");
        }

        customRobot.performBehavior();
    }
}
