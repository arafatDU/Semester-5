package product;

public class VictorianChair implements Chair{
    @Override
    public void hasLegs() {
        System.out.println("Victorian Chair has 5 legs.");
    }

    @Override
    public void sitOn() {
        System.out.println("Sit on the Victorian chair");
    }
}
