package behavior;

public class NormalBehavior implements Behavior {
    @Override
    public void execute(String robotName) {
        System.out.println(robotName + " is behaving normally, moving casually.");
    }
}
