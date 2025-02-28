package behavior;

public class AggressiveBehavior implements Behavior {
    @Override
    public void execute(String robotName) {
        System.out.println(robotName + " is behaving aggressively, attacking obstacles.");
    }
}
