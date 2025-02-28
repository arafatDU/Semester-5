package builders;

import components.Bread;
import components.Filling;
import components.Spread;

public interface SandwichBuilder {
    void setBread(Bread bread);
    void setFilling(Filling filling);
    void setSpread(Spread spread);
}
