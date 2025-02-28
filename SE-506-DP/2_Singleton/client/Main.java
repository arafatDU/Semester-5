package client;

import factory.Furniture;
import factory.ModernFurniture;
import factory.RusticFurniture;
import factory.VictorianFurniture;
import product.Chair;
import product.Sofa;
import product.Table;

public class Main {
    public static void main(String[] args) {
        Furniture modern = new ModernFurniture();
        Chair modernChair = modern.createChair();
        modernChair.sitOn();

        Furniture victorian = new VictorianFurniture();
        Table victorianTable = victorian.createTable();
        victorianTable.eatOn();


        Furniture rustic = new RusticFurniture();
        Sofa rusticSofa = rustic.createSofa();
        rusticSofa.lieOn();
    }
}
