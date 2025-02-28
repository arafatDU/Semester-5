package product;

public abstract class Product {
    protected String name;
    protected String category;
    protected double price;

    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public abstract Product clone();
    public abstract String getInfo();
}
