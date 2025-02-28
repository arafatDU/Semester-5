package behavior;

public class DefensiveBehavior implements Behavior {
    @Override
    public void execute(String robotName) {
        System.out.println(robotName + " is behaving defensively, avoiding threats.");
    }
}
