package director;

import builders.ChickenSandwichBuilder;
import builders.EggSandwichBuilder;
import components.Bread;
import components.Filling;
import components.Spread;
import products.ChickenSandwich;
import products.EggSandwich;

public class Director {
    public ChickenSandwich makeChickenSandwich(ChickenSandwichBuilder builder) {
        builder.setBread(new Bread("Regular Bread"));
        builder.setFilling(new Filling("Grilled Chicken"));
        builder.setSpread(new Spread("Cheese"));
        return builder.getSandwich();
    }

    public EggSandwich makeEggSandwich(EggSandwichBuilder builder) {
        builder.setBread(new Bread("Toasted Bread"));
        builder.setFilling(new Filling("Fried Egg"));
        builder.setSpread(new Spread("Sauce"));
        return builder.getSandwich();
    }

    public ChickenSandwich makeCustomChickenSandwich(ChickenSandwichBuilder builder, String breadType, String fillingType, String spreadType) {
        builder.setBread(new Bread(breadType));
        builder.setFilling(new Filling(fillingType));
        builder.setSpread(new Spread(spreadType));
        return builder.getSandwich();
    }
}
