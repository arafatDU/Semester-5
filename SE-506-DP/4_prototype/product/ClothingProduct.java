package product;

public class ClothingProduct extends Product {
    private String color;
    private String size;

    public ClothingProduct(String name, String category, double price, String color, String size) {
        super(name, category, price); // Using super() to initialize common attributes
        this.color = color;
        this.size = size;
    }

    @Override
    public Product clone() {
        return new ClothingProduct(name, category, price, color, size);
    }

    @Override
    public String getInfo() {
        return "ClothingProduct [name=" + name + ", category=" + category + ", price=" + price +
                ", color=" + color + ", size=" + size + "]";
    }

    // Setter to customize the clone
    public void setColor(String color) {
        this.color = color;
    }
}
