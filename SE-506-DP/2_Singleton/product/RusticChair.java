package product;

public class RusticChair implements Chair{
    @Override
    public void hasLegs() {
        System.out.println("Rustic Chair has 5 legs.");
    }

    @Override
    public void sitOn() {
        System.out.println("Sit on the Rustic chair");
    }
}
