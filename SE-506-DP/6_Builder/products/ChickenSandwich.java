package products;

import components.Bread;
import components.Filling;
import components.Spread;

public class ChickenSandwich {
    private Bread bread;
    private Filling filling;
    private Spread spread;

    public ChickenSandwich(Bread bread, Filling filling, Spread spread) {
        this.bread = bread;
        this.filling = filling;
        this.spread = spread;
    }

    @Override
    public String toString() {
        return "Chicken Sandwich [Bread: " + bread + ", Filling: " + filling + ", Spread: " + spread + "]";
    }
}
