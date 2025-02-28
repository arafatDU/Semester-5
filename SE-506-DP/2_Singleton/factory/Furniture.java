package factory;

import product.Chair;
import product.Sofa;
import product.Table;

public abstract class Furniture {
    public abstract Chair createChair();
    public abstract Table createTable();
    public abstract Sofa createSofa();

}
