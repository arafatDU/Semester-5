package context;

import behavior.Behavior;

public class RobotContext {
    private String name;
    private Behavior behavior;

    public RobotContext(String name, Behavior behavior) {
        this.name = name;
        this.behavior = behavior;
    }

    public void setBehavior(Behavior behavior) {
        this.behavior = behavior;
        System.out.println(name + " has switched to a new behavior.");
    }

    public void performBehavior() {
        behavior.execute(name);
    }

    public String getName() {
        return name;
    }
}
