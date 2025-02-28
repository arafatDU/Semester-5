package client;

import product.ClothingProduct;
import product.ElectronicProduct;
import product.Product;
import registry.PrototypeRegistry;

public class Main {
    public static void main(String[] args) {
        PrototypeRegistry registry = new PrototypeRegistry();


        registry.addPrototype("TShirt", new ClothingProduct("Basic T-Shirt", "Clothing", 19.99, "Blue", "M"));
        registry.addPrototype("Smartphone", new ElectronicProduct("Basic Smartphone", "Electronics", 299.99, 64, 10));


        Product tshirt1 = registry.getPrototype("TShirt");
        System.out.println(((ClothingProduct) tshirt1).getInfo());

        Product tshirt2 = registry.getPrototype("TShirt");
        ((ClothingProduct) tshirt2).setColor("Red"); // Customize the clone
        System.out.println("Customized T-shirt 2: " + ((ClothingProduct) tshirt2).getInfo());

        // Customized electronic product
        Product smartphone1 = registry.getPrototype("Smartphone");
        System.out.println(((ElectronicProduct) smartphone1).getInfo());

        Product smartphone2 = registry.getPrototype("Smartphone");
        System.out.println(smartphone2.getInfo());
    }
}
