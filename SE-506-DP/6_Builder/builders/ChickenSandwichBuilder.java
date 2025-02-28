package builders;

import components.Bread;
import components.Filling;
import components.Spread;
import products.ChickenSandwich;

public class ChickenSandwichBuilder implements SandwichBuilder {
    private Bread bread;
    private Filling filling;
    private Spread spread;

    @Override
    public void setBread(Bread bread) {
        this.bread = bread;
    }

    @Override
    public void setFilling(Filling filling) {
        this.filling = filling;
    }

    @Override
    public void setSpread(Spread spread) {
        this.spread = spread;
    }

    public ChickenSandwich getSandwich() {
        return new ChickenSandwich(bread, filling, spread);
    }
}
