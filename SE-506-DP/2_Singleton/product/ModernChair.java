package product;

public class ModernChair implements Chair{
    @Override
    public void hasLegs() {
        System.out.println("Modern Chair has 5 legs.");
    }

    @Override
    public void sitOn() {
        System.out.println("Sit on the Modern chair");
    }
}
