package product;

public class ElectronicProduct extends Product {
    private int storageCapacity;
    private int batteryLife;

    public ElectronicProduct(String name, String category, double price, int storageCapacity, int batteryLife) {
        super(name, category, price); // Using super() to initialize common attributes
        this.storageCapacity = storageCapacity;
        this.batteryLife = batteryLife;
    }

    @Override
    public Product clone() {
        return new ElectronicProduct(name, category, price, storageCapacity, batteryLife);
    }

    @Override
    public String getInfo() {
        return "ElectronicProduct [name=" + name + ", category=" + category + ", price=" + price +
                ", storageCapacity=" + storageCapacity + "GB, batteryLife=" + batteryLife + "hrs]";
    }
}
