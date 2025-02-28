package products;

import components.Bread;
import components.Filling;
import components.Spread;

public class EggSandwich {
    private Bread bread;
    private Filling filling;
    private Spread spread;

    public EggSandwich(Bread bread, Filling filling, Spread spread) {
        this.bread = bread;
        this.filling = filling;
        this.spread = spread;
    }

    @Override
    public String toString() {
        return "Egg Sandwich [Bread: " + bread + ", Filling: " + filling + ", Spread: " + spread + "]";
    }
}
