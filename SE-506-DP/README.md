# Design Pattern

## 1. Factory Design Pattern


> Consider a software system that needs to handle the creation of different types of _devices_—such as _Smartphone_, _Tablet_, and _Laptop_—each with distinct properties, behaviors, and mandatory _powerOn_ and _powerOff_ method. You must implement the **factory method pattern** to instantiate new devices.



client/Main.java

```
package client;

import factory.DeviceFactory;
import factory.LaptopFactory;
import factory.SmartphoneFactory;
import factory.TabletFactory;
import model.Device;

public class Main {
    public static void main(String[] args) {
        DeviceFactory smartphnFactory = new SmartphoneFactory();
        Device smartphn = smartphnFactory.createDevice("Samsung", 3, 8);
        smartphn.powerOff();
        System.out.println(smartphn);

        DeviceFactory tabletFactory = new TabletFactory();
        Device tablet = tabletFactory.createDevice("iPad", 10.7, 256);
        tablet.powerOff();
        System.out.println(tablet);

        DeviceFactory laptopFactory =  new LaptopFactory();
        Device laptop = laptopFactory.createDevice("MacOS", 8, 256);
        laptop.aboutDevice();
        System.out.println(laptop);


    }
}


package factory;

import model.Device;

public abstract class DeviceFactory {
    public abstract Device createDevice(Object... params);
}

package factory;

import model.Device;
import model.Laptop;

public class LaptopFactory extends DeviceFactory{
    @Override
    public Device createDevice(Object... laptopParams) {
        return new Laptop(laptopParams);
    }
}



package model;

public class Laptop implements Device{
    private String os;
    private int ram;
    private int ssd;

    public Laptop(Object... laptopParams) {
        this.os = (String) laptopParams[0];
        this.ram = (int) laptopParams[1];
        this.ssd = (int) laptopParams[2];
    }

    @Override
    public void powerOn() {
        System.out.println("Laptop's power is on.");
    }

    @Override
    public void powerOff() {
        System.out.println("Laptop's power is off.");
    }

    @Override
    public void aboutDevice() {
        System.out.println("This is a Laptop.");
    }

    @Override
    public String toString() {
        return "Smartphone [ OS = " + os + ", RAM = " + ram + " GB" +
                ", SSD =" + ssd + " GB]";
    }
}
```










---

## 2. Abstract Factory Design Pattern


>  You are tasked with designing a software system for a furniture company that manufactures different styles of furniture, including Modern, Victorian, and Rustic. Each style includes products like Chair, Table and Sofa. You need to ensure that a complete set of furniture (Chair, Table, Sofa) produced for each style is consistent. Implement the scenario using the Abstract Factory pattern.



```
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

-------------------------------------------------------
package factory;

import product.Chair;
import product.Sofa;
import product.Table;

public abstract class Furniture {
    public abstract Chair createChair();
    public abstract Table createTable();
    public abstract Sofa createSofa();

}
-------------------------------------------------------

package factory;

import product.*;

public class ModernFurniture extends Furniture{
    @Override
    public Chair createChair() {
        return new ModernChair();
    }

    @Override
    public Table createTable() {
        return new ModernTable();
    }

    @Override
    public Sofa createSofa() {
        return new ModernSofa();
    }
}

-------------------------------------------------------
package product;

public interface Chair {
    public void hasLegs();
    public void sitOn();
}
-------------------------------------------------------
package product;

public class ModernChair implements Chair{
    @Override
    public void hasLegs() {
        System.out.println("Modern Chair has 5 legs.");
    }

    @Override
    public void sitOn() {
        System.out.println("Sit on the Modern chair");
    }
}
```








---

## 3. Singleton 


> Let's assume that each furniture product (e.g., Chair, Table, and Sofa) is manufactured with unique IDs. In a multithreading environment, if the factory is instantiated twice in two different threads, it is possible to have overlapping IDs for different furniture products. To avoid this issue, implement the Singleton pattern alongside the Abstract Factory solution from Part 1.



```
package product;
public interface Printer {
    public void print(String document);
}

-------------------------------------------------------
package product;
public class ConcretePrinter implements Printer {
    private final String departmentName;

    // Constructor to assign a department to this printer
    public ConcretePrinter(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public void print(String document) {
        System.out.println("Printing document for department: " + departmentName + " - Document: " + document);
    }
}

-------------------------------------------------------

package factory;
import product.ConcretePrinter;
import product.Printer;

import java.util.HashMap;
import java.util.Map;

public class PrinterFactory {

    private static volatile PrinterFactory instance;
    private static volatile Map<String, Printer> printerRegistry;

    private PrinterFactory() {
        printerRegistry = new HashMap<>();
    }

    public static PrinterFactory getInstance() {
        PrinterFactory result = instance;
        if (result != null) {
            return result;
        }
        synchronized (PrinterFactory.class) {
            if (instance == null) {
                instance = new PrinterFactory();
            }
            return instance;
        }
    }

    public Printer getPrinter(String departmentName) {

        Printer printer = printerRegistry.get(departmentName);
        if (printer == null) {
            synchronized (this) {
                if (printerRegistry.get(departmentName) == null) {
                    printer = new ConcretePrinter(departmentName);
                    printerRegistry.put(departmentName, printer);
                    //System.out.println("Created new printer for department: " + departmentName);
                } else {
                    printer = printerRegistry.get(departmentName);
                }
            }
        } else {
            System.out.println("Reusing existing printer for department: " + departmentName);
        }

        return printer;
    }
}

-------------------------------------------------------
package client;
import factory.PrinterFactory;
import product.Printer;

public class Main {
    public static void main(String[] args) {

        PrinterFactory printerFactory = PrinterFactory.getInstance();

        //HR department
        Printer hrPrinter1 = printerFactory.getPrinter("HR");
        hrPrinter1.print("HR Report 1");

        //IT department
        Printer itPrinter = printerFactory.getPrinter("IT");
        itPrinter.print("IT Security Report");

        // Reusing printer for the 'HR' department
        Printer hrPrinter2 = printerFactory.getPrinter("HR");
        hrPrinter2.print("HR Report 2");

    }
}
```
```
package factory;

import product.Chair;
import product.Sofa;

public abstract class FurnitureFactory {
    public abstract Chair createChair();
    public abstract Sofa createSofa();
}

-------------------------------------------------------

package factory;
import product.Chair;
import product.ModernChair;
import product.ModernSofa;
import product.Sofa;

public class ModernFurnitureFactory extends FurnitureFactory {

    @Override
    public Chair createChair() {
        return new ModernChair();
    }

    @Override
    public Sofa createSofa() {
        return new ModernSofa();
    }
}

-------------------------------------------------------
package factory;

import java.util.HashMap;
import java.util.Map;

public class SingletonFurnitureFactory {

    // Singleton instance
    private static volatile SingletonFurnitureFactory instance;
    // Registry to store furniture factories
    private static volatile Map<String, FurnitureFactory> factoryRegistry;
    // Private constructor to initialize the registry
    private SingletonFurnitureFactory() {
        factoryRegistry = new HashMap<>();
    }

    // Method to get the Singleton instance
    public static SingletonFurnitureFactory getInstance() {
        if (instance == null) {
            synchronized (SingletonFurnitureFactory.class) {
                if (instance == null) {
                    instance = new SingletonFurnitureFactory();
                }
            }
        }
        return instance;
    }

    // Method to get or create a furniture factory
    public FurnitureFactory getFactory(String style) {
        FurnitureFactory factory = factoryRegistry.get(style.toLowerCase());

        if (factory == null) {
            synchronized (this) {
                if (!factoryRegistry.containsKey(style.toLowerCase())) {
                    factory = createFactory(style.toLowerCase());
                    factoryRegistry.put(style.toLowerCase(), factory);
                } else {
                    factory = factoryRegistry.get(style.toLowerCase());
                }
            }
        }

        return factory;
    }

    // Private method to create a factory based on the style
    private FurnitureFactory createFactory(String style) {
        switch (style) {
            case "modern":
                return new ModernFurnitureFactory();
            case "victorian":
                return new VictorianFurnitureFactory();
            default:
                throw new IllegalArgumentException("Unknown furniture style: " + style);
        }
    }
}

-------------------------------------------------------
package client;
import factory.*;
import product.*;

public class Main {

    public static void main(String[] args) {
        // Get the singleton instance of SingletonFurnitureFactory
        SingletonFurnitureFactory furnitureFactoryManager = SingletonFurnitureFactory.getInstance();

        // Test Modern Furniture
        System.out.println("Testing Modern Furniture:");
        FurnitureFactory modernFactory = furnitureFactoryManager.getFactory("modern");
        Chair modernChair = modernFactory.createChair();
        Sofa modernSofa = modernFactory.createSofa();

        modernChair.sit();
        modernSofa.rest();

        // Test Victorian Furniture
        System.out.println("\nTesting Victorian Furniture:");
        FurnitureFactory victorianFactory = furnitureFactoryManager.getFactory("victorian");
        Chair victorianChair = victorianFactory.createChair();
        Sofa victorianSofa = victorianFactory.createSofa();

        victorianChair.sit();
        victorianSofa.rest();
    }
}
```






---

## 4. Builder Design Pattern


>  Consider a food shop where customers can order customizable sandwiches. The system allows customers to specify each component of the sandwich individually—such as the type of bread, the filling and the spread or accompaniment. For example, a chicken sandwich might be assembled with regular bread, grilled chicken, and cheese. An egg sandwich could include toasted bread, fried egg and sauce. The system also shows the full menu of sandwiches to the customer.





```
package components;

public class Bread {
    private String type;

    public Bread(String type) {
        this.type = type;
    }

}
---------------------------------------------------------
package components;

public class Filling {
    private String type;

    public Filling(String type) {
        this.type = type;
    }

}

---------------------------------------------------------
package builders;

import components.Bread;
import components.Filling;
import components.Spread;

public interface SandwichBuilder {
    void setBread(Bread bread);
    void setFilling(Filling filling);
    void setSpread(Spread spread);
}

---------------------------------------------------------
package products;

import components.Bread;
import components.Filling;
import components.Spread;

public class ChickenSandwich {
    private Bread bread;
    private Filling filling;
    private Spread spread;

    public ChickenSandwich(Bread bread, Filling filling, Spread spread) {
        this.bread = bread;
        this.filling = filling;
        this.spread = spread;
    }

    @Override
    public String toString() {
        return "Chicken Sandwich [Bread: " + bread + ", Filling: " + filling + ", Spread: " + spread + "]";
    }
}


--------------------------------------------------------
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

-------------------------------------------------------
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

}

-------------------------------------------------------
package builders;

import components.Bread;
import components.Filling;
import components.Spread;
import products.EggSandwich;

public class EggSandwichBuilder implements SandwichBuilder {
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

    public EggSandwich getSandwich() {
        return new EggSandwich(bread, filling, spread);
    }
}

-------------------------------------------------------
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

-------------------------------------------------------
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
```
```
package client;

import builders.ChickenSandwichBuilder;
import builders.EggSandwichBuilder;
import director.Director;
import products.ChickenSandwich;
import products.EggSandwich;

public class Main {
    public static void main(String[] args) {
        Director director = new Director();

        // Build predefined sandwiches
        ChickenSandwich chickenSandwich = director.makeChickenSandwich(new ChickenSandwichBuilder());
        EggSandwich eggSandwich = director.makeEggSandwich(new EggSandwichBuilder());

        // Display the full menu
        System.out.println("Full Menu:");
        System.out.println("1. " + chickenSandwich);
        System.out.println("2. " + eggSandwich);

        // Build a custom chicken sandwich
        ChickenSandwich customChickenSandwich = director.makeCustomChickenSandwich(
                new ChickenSandwichBuilder(), "Whole Wheat Bread", "Spicy Grilled Chicken", "Honey Mustard"
        );

        System.out.println("\nCustom Order:");
        System.out.println(customChickenSandwich);
    }
}
```






---

## 5. Prototype Design Pattern


>  Suppose you are developing a product catalog system for an e-commerce platform. In this platform, many products share core attributes (e.g., name, category, price) but require specific customizations. For example, clothing items may come in multiple colors and sizes, while electronic products may vary in storage capacities and battery life. You have to implement the Prototype design pattern to create product variants with slight customizations. To manage these prototypes, you also need to implement a prototype registry.



```
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
```
```
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

package product;


-------------------------------------------------------
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
```




```
package registry;

import product.Product;
import java.util.HashMap;
import java.util.Map;

public class PrototypeRegistry {
    private Map<String, Product> prototypes = new HashMap<>();

    // Register a prototype with a key
    public void addPrototype(String key, Product prototype) {
        prototypes.put(key, prototype);
    }

    // Retrieve and clone a prototype
    public Product getPrototype(String key) {
        Product prototype = prototypes.get(key);
        if (prototype != null) {
            return prototype.clone();
        }
        throw new IllegalArgumentException("Prototype not found for key: " + key);
    }
}
```
```
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
```


---

## 6. Facade Design Pattern
>  In a pizza delivery system, ordering a pizza with a credit card involves several subsystems that work together to complete the ordering process. For example, checking account, checking security PIN, credit/debit balance, making ledger entry, sending notification etc. You have to implement the Facade design pattern in this scenario to provide a simplified interface for client to order a pizza allowing them to input only essential details (card information, security PIN, payment amount, and operation type).



```
package pizza;

public class AccountService {
    public boolean verifyAccount(String cardNumber) {
        // Simulate account verification (in a real system, this would query a database or API)
        return cardNumber != null && !cardNumber.isEmpty();
    }
}

-------------------------------------------------------
package pizza;

import java.util.HashMap;
import java.util.Map;

public class BalanceService {
    private Map<String, Double> accountBalances = new HashMap<>();

    public BalanceService() {
        accountBalances.put("1234567890", 100.0); // Sample account with balance
    }

    public boolean checkBalance(String cardNumber, double amount) {
        return accountBalances.getOrDefault(cardNumber, 0.0) >= amount;
    }

    public void deductAmount(String cardNumber, double amount) {
        accountBalances.put(cardNumber, accountBalances.get(cardNumber) - amount);
        System.out.println("Amount deducted: " + amount);
    }
}

-------------------------------------------------------
package pizza;

public class LedgerService {
    public void makeEntry(String cardNumber, double amount, String operation) {
        System.out.println("Ledger entry made for card: " + cardNumber + ", amount: " + amount + ", operation: " + operation);
    }
}
-------------------------------------------------------
package pizza;

public class SecurityService {
    public boolean checkPin(String cardNumber, String pin) {
        // Simulate PIN check (in a real system, this would query a secure database)
        return pin.equals("1234"); // For simplicity, we assume PIN "1234" is valid
    }
}

-------------------------------------------------------
package pizza;

public class PizzaOrderFacade {
    private AccountService accountService;
    private SecurityService securityService;
    private BalanceService balanceService;
    private LedgerService ledgerService;
    private NotificationService notificationService;

    public PizzaOrderFacade() {
        this.accountService = new AccountService();
        this.securityService = new SecurityService();
        this.balanceService = new BalanceService();
        this.ledgerService = new LedgerService();
        this.notificationService = new NotificationService();
    }

    public void orderPizza(String cardNumber, String pin, double amount) {
        if (!accountService.verifyAccount(cardNumber)) {
            System.out.println("Invalid account number.");
            return;
        }

        if (!securityService.checkPin(cardNumber, pin)) {
            System.out.println("Invalid PIN.");
            return;
        }

        if (!balanceService.checkBalance(cardNumber, amount)) {
            System.out.println("Insufficient balance.");
            return;
        }

        balanceService.deductAmount(cardNumber, amount);
        ledgerService.makeEntry(cardNumber, amount, "Pizza Order");
        notificationService.sendNotification("Your pizza order has been placed successfully!");

        System.out.println("Pizza order completed successfully!");
    }
}
```




## 7. Proxy Design Pattern
>  Consider an image viewer program that displays high-resolution photos. To optimize memory use and keep the application responsive, it avoids loading large files of high-resolution photos until needed. However, the program lists all photos with filenames but only loads the full image when the user selects it from the list.





```
package image;

public interface Image {
    void display();
    String getFilename();
}
```
```
package image;

public class RealImage implements Image {
    private String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadImageFromDisk(); // Load image upon creation
    }

    private void loadImageFromDisk() {
        System.out.println("Loading high-resolution image: " + filename);
    }

    @Override
    public void display() {
        System.out.println("Displaying high-resolution image: " + filename);
    }

    @Override
    public String getFilename() {
        return filename;
    }
}
```
```
package image;

public class ImageProxy implements Image {
    private String filename;
    private RealImage realImage;

    public ImageProxy(String filename) {
        this.filename = filename;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename); // Load real image only when display() is called
        }
        else {
            System.out.println("Loaded From Cached: " + filename);
        }
        realImage.display();
    }

    @Override
    public String getFilename() {
        return filename;
    }
}
```


```
package client;

import image.Image;
import image.ImageProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ImageViewer {
    private List<Image> images;

    public ImageViewer() {
        images = new ArrayList<>();
        loadImages();
    }

    private void loadImages() {
        images.add(new ImageProxy("Beach.jpg"));
        images.add(new ImageProxy("Mountain.jpg"));
        images.add(new ImageProxy("Cityscape.jpg"));
        images.add(new ImageProxy("Desert.jpg"));
    }

    public void displayImageList() {
        System.out.println("Image List:");
        for (int i = 0; i < images.size(); i++) {
            System.out.println((i + 1) + ". " + images.get(i).getFilename());
        }
    }

    public void viewImage(int index) {
        if (index >= 1 && index <= images.size()) {
            Image image = images.get(index - 1);
            image.display();
        } else {
            System.out.println("Invalid image selection.");
        }
    }

    public static void main(String[] args) {
        ImageViewer imageViewer = new ImageViewer();
        imageViewer.displayImageList();

        Scanner scanner = new Scanner(System.in);


        while(true) {
            System.out.print("Select an image to view (enter number): ");
            int choice = scanner.nextInt();
            imageViewer.viewImage(choice);

            if(choice == 0) break;
        }

    }
}
```




---

## 8. Adapter Design Pattern
> A company uses a new printing management application to handle various printing jobs. However, several older printer models are still in use. These legacy printers are incompatible with the new application because they rely on an outdated interface. To address this compatibility issue, you need to apply a design pattern to allow the new system to work seamlessly with both modern and legacy printers. The application will be able to:
treat both modern and legacy printers uniformly
print documents without needing to know the printer type
_You need to implement and demonstrate both variations of Adapter Design Pattern (Object Adapter and Class Adapter)._





```
package adapter;

public interface PrinterClientInterface {
    void printDocument(String content);
}
```
```
package adapter;

public class ModernPrinter implements PrinterClientInterface{
    @Override
    public void printDocument(String content) {
        System.out.println("Modern Printer: Printing -> " + content);
    }
}



package adapter;

public class LegacyPrinter {
    public void printOldWay(String content) {
        System.out.println("Legacy Printer: Printing in an old-fashioned way -> " + content);
    }
}
```
```
package adapter;

public class LegacyPrinterObjectAdapter implements PrinterClientInterface {
    private final LegacyPrinter legacyPrinter;

    public LegacyPrinterObjectAdapter(LegacyPrinter legacyPrinter) {
        this.legacyPrinter = legacyPrinter;
    }

    @Override
    public void printDocument(String content) {
        // Translating the client’s call into a call that the legacy printer can understand
        legacyPrinter.printOldWay(content);
    }
}
```
```
package adapter;

public class LegacyPrinterClassAdapter extends LegacyPrinter implements PrinterClientInterface {
    @Override
    public void printDocument(String content) {
        // Directly calling the inherited method from LegacyPrinter
        printOldWay(content);
    }
}
```
```
package client;

import adapter.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PrinterClientInterface modernPrinter = new ModernPrinter();
        modernPrinter.printDocument("Hello from Modern Printer!");


        LegacyPrinter legacyPrinter = new LegacyPrinter();
        PrinterClientInterface legacyPrinterObjectAdapter = new LegacyPrinterObjectAdapter(legacyPrinter);
        legacyPrinterObjectAdapter.printDocument("Hello from Legacy Printer using Object Adapter!");


        PrinterClientInterface legacyPrinterClassAdapter = new LegacyPrinterClassAdapter();
        legacyPrinterClassAdapter.printDocument("Hello from Legacy Printer using Class Adapter!");


        List<PrinterClientInterface> printers = new ArrayList<>();
        printers.add(modernPrinter);
        printers.add(legacyPrinterObjectAdapter);
        printers.add(legacyPrinterClassAdapter);

        for (PrinterClientInterface printer : printers) {
            printer.printDocument("Hello from the Unified Printing System!");
        }
    }
}
```






## Mixed Question From Factory, Singleton & Proxy
Design and implement a library management system using the **Factory**, **Proxy**, and **Singleton** patterns. In this system, Users can borrow different types of library items (e.g., Books, Magazines, etc.). The access to restricted items is controlled through a proxy. A singleton configuration manager handles library-wide settings like late fees and opening hours. You can start with the following steps:

- Define a **LibraryItem **interface with methods like getDetails() and borrowItem().
- Implement concrete classes **Book**, **Magazine**, and other types.
- Create a LibraryItemFactory class to generate the appropriate LibraryItem object based on user input.
- Define a **LibraryAccess **interface with a method accessItem(String itemID, User user).
- Implement a concrete class **RealLibraryAccess **to provide direct access to library items.
- Create a **LibraryAccessProxy **class that checks user permissions before allowing access to restricted items.
- Create a **LibraryConfigManager **class to store system-wide settings like late fees, borrowing limits, and library hours.
Ensure only one instance of LibraryConfigManager exists and provide methods to retrieve and update settings.



```
package libraryitems;

public interface LibraryItem {
    String getDetails();
    void borrowItem();
}
-----------------------------------------------------------

package libraryitems;

public class Book implements LibraryItem {
    private String title;
    private String author;
    private String ISBN;

    public Book(Object... args) {
        this.title = args[0].toString();
        this.author = args[1].toString();
        this.ISBN = args[2].toString();
    }

    @Override
    public String getDetails() {
        return "Book: " + title + " by " + author + " (ISBN: " + ISBN + ")";
    }

    @Override
    public void borrowItem() {
        System.out.println("Borrowing Book: " + title);
    }
}

-----------------------------------------------------------

package libraryitems;

public class Magazine implements LibraryItem {
    private String title;
    private String issue;

    public Magazine(Object... args) {
        this.title = args[0].toString();
        this.issue = args[1].toString();
    }

    @Override
    public String getDetails() {
        return "Magazine: " + title + " (Issue: " + issue + ")";
    }

    @Override
    public void borrowItem() {
        System.out.println("Borrowing Magazine: " + title);
    }
}
```
```
package factory;

import libraryitems.Book;
import libraryitems.LibraryItem;
import libraryitems.Magazine;

public interface LibraryItemFactory {
    public LibraryItem createLibraryItem(Object... args);
}

-----------------------------------------------------------
package factory;

import libraryitems.Book;
import libraryitems.LibraryItem;

public class BookFactory implements LibraryItemFactory {

    @Override
    public LibraryItem createLibraryItem(Object... args) {
        return new Book(args);
    }
}

-----------------------------------------------------------
package factory;

import libraryitems.LibraryItem;
import libraryitems.Magazine;

public class MagazineFactory implements LibraryItemFactory {

    @Override
    public LibraryItem createLibraryItem(Object... args) {
        return new Magazine(args);
    }
}
```
```
package access;

import libraryitems.LibraryItem;
import user.User;

public interface LibraryAccess {
    LibraryItem accessItem(String itemID, User user);
}

-----------------------------------------------------------
package access;

import libraryitems.LibraryItem;
import user.User;
import java.util.HashMap;
import java.util.Map;

public class RealLibraryAccess implements LibraryAccess {
    private Map<String, LibraryItem> libraryItems = new HashMap<>();

    public void addItem(String itemID, LibraryItem item) {
        libraryItems.put(itemID, item);
    }

    @Override
    public LibraryItem accessItem(String itemID, User user) {
        return libraryItems.get(itemID);
    }
}

-----------------------------------------------------------
package access;

import libraryitems.LibraryItem;
import user.User;

public class LibraryAccessProxy implements LibraryAccess {
    private RealLibraryAccess realLibraryAccess;

    public LibraryAccessProxy(RealLibraryAccess realLibraryAccess) {
        this.realLibraryAccess = realLibraryAccess;
    }

    @Override
    public LibraryItem accessItem(String itemID, User user) {
        if (user.hasAccess()) {
            System.out.println("Access granted to " + user.getName() + " for item " + itemID);
            return realLibraryAccess.accessItem(itemID, user);
        } else {
            System.out.println("Access denied to " + user.getName() + " for item " + itemID);
            return null;
        }
    }
}
```
```
package config;

public class LibraryConfigManager {
    private static LibraryConfigManager instance;
    private double lateFee;
    private String openingHours;

    private LibraryConfigManager() {
        this.lateFee = 0.5; // Default late fee
        this.openingHours = "9:00 AM - 5:00 PM";
    }

    public static synchronized LibraryConfigManager getInstance() {
        if (instance == null) {
            instance = new LibraryConfigManager();
        }
        return instance;
    }

    public double getLateFee() {
        return lateFee;
    }

    public void setLateFee(double lateFee) {
        this.lateFee = lateFee;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }
}
```
```
package user;

public class User {
    private String name;
    private boolean hasAccess;

    public User(String name, boolean hasAccess) {
        this.name = name;
        this.hasAccess = hasAccess;
    }

    public String getName() {
        return name;
    }

    public boolean hasAccess() {
        return hasAccess;
    }
}

-----------------------------------------------------------

package client;

import access.LibraryAccess;
import access.LibraryAccessProxy;
import access.RealLibraryAccess;
import config.LibraryConfigManager;
import factory.BookFactory;
import factory.LibraryItemFactory;
import factory.MagazineFactory;
import libraryitems.LibraryItem;
import user.User;

public class Main {
    public static void main(String[] args) {
        LibraryItemFactory bookFactory = new BookFactory();
        LibraryItemFactory magazineFactory = new MagazineFactory();

        LibraryItem book1 = bookFactory.createLibraryItem("Book", "Design Patterns", "Erich Gamma", "1234567890");
        LibraryItem magazine1 = magazineFactory.createLibraryItem("Magazine", "Tech Today", "March 2023", null);


        RealLibraryAccess realAccess = new RealLibraryAccess();
        realAccess.addItem("B1", book1);
        realAccess.addItem("M1", magazine1);


        LibraryAccess libraryProxy = new LibraryAccessProxy(realAccess);


        User user1 = new User("Alice", true);  // User with access
        User user2 = new User("Bob", false);   // User without access

        // Access items
        LibraryItem item1 = libraryProxy.accessItem("B1", user1);
        if (item1 != null) {
            item1.borrowItem();
        }

        LibraryItem item2 = libraryProxy.accessItem("M1", user2);
        if (item2 != null) {
            item2.borrowItem();
        }

        // Configure library settings
        LibraryConfigManager configManager = LibraryConfigManager.getInstance();
        System.out.println("Library Late Fee: $" + configManager.getLateFee());
        System.out.println("Library Hours: " + configManager.getOpeningHours());
    }
}
```




---

## 9. Composite Design Pattern
> Consider a file system of an operating system. In the file system, there are two types of objects: **File **and **Folder**. There are cases when **File **and **Folder **are treated to be the same way. For example, a search operation of a particular keyword needs to be executed. Now, this search operation applies to both **File **and **Folder**. For a **File**, it will just look into the contents of the file and for a Folder, it will go through all files in the hierarchy in that folder to find that keyword.

Design and implement the above scenario using the Composite design pattern.



```
package filesystem;

public interface FileSystemComponent {
    boolean search(String keyword);
}

-----------------------------------------------------------
package filesystem;

public class File implements FileSystemComponent {
    private String name;
    private String content;

    public File(String name, String content) {
        this.name = name;
        this.content = content;
    }

    @Override
    public boolean search(String keyword) {
        if (content.contains(keyword)) {
            System.out.println("Keyword found in File: " + name);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "File: " + name;
    }
}
-----------------------------------------------------------

package filesystem;

import java.util.ArrayList;
import java.util.List;

public class Folder implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> components;

    public Folder(String name) {
        this.name = name;
        this.components = new ArrayList<>();
    }

    public void addComponent(FileSystemComponent component) {
        components.add(component);
    }

    public void removeComponent(FileSystemComponent component) {
        components.remove(component);
    }

    public List<FileSystemComponent> getComponents() {
        return components;
    }

    @Override
    public boolean search(String keyword) {
        System.out.println("Searching in Folder: " + name);
        boolean found = false;

        for (FileSystemComponent component : components) {
            if (component.search(keyword)) {
                found = true;
            }
        }

        if (!found) {
            System.out.println("Keyword not found in Folder: " + name);
        }
        return found;
    }

    @Override
    public String toString() {
        return "Folder: " + name;
    }
}
```
```
package client;

import filesystem.File;
import filesystem.FileSystemComponent;
import filesystem.Folder;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        File file1 = new File("file1.txt", "This is the content of file1");
        File file2 = new File("file2.txt", "This is some other content");
        File file3 = new File("file3.txt", "keyword content is here");


        Folder folder1 = new Folder("Folder1");
        folder1.addComponent(file1);
        folder1.addComponent(file2);


        Folder folder2 = new Folder("Folder2");
        folder2.addComponent(file3);
        folder2.addComponent(folder1);


        Scanner scanner = new Scanner(System.in);
        file1.search("content");

        folder1.search("content");
    }


}
```




---

## 10. Decorator Design Pattern
> Imagine a coffee shop where you can customize your coffee order. You start with a basic coffee, and you can add different ingredients like milk, sugar, whipped cream, and so on. The base coffee object can be decorated with additional functionality (flavors, toppings) dynamically. For example, you can start with a plain coffee object, then wrap it with a milk decorator, followed by a sugar decorator, and finally a whipped cream decorator. Each decorator adds new features or modifies the behavior of the coffee object.

Design and implement the above scenario using the Decorator design pattern.



```
package coffee;

public interface Coffee {
    String getDescription();
    double getCost();
}

-----------------------------------------------------------

package coffee;

public class PlainCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Plain Coffee";
    }

    @Override
    public double getCost() {
        return 2.0;
    }
}
```
```
package decorators;

import coffee.Coffee;

public abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription();
    }

    @Override
    public double getCost() {
        return coffee.getCost();
    }
}
-----------------------------------------------------------

package decorators;

import coffee.Coffee;

public class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Milk";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 0.5;
    }
}

-----------------------------------------------------------

package decorators;

import coffee.Coffee;

public class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Sugar";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 0.2; // Add cost of sugar
    }
}

-----------------------------------------------------------

package decorators;

import coffee.Coffee;

public class WhippedCreamDecorator extends CoffeeDecorator {
    public WhippedCreamDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Whipped Cream";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 1.0;
    }
}
```
```
package client;

import coffee.Coffee;
import coffee.PlainCoffee;
import decorators.MilkDecorator;
import decorators.SugarDecorator;
import decorators.WhippedCreamDecorator;

public class Main {
    public static void main(String[] args) {

        Coffee coffee = new PlainCoffee();
        System.out.println(coffee.getDescription() + " -> $" + coffee.getCost());

        // Add milk
//        coffee = new MilkDecorator(coffee);
//        System.out.println(coffee.getDescription() + " -> $" + coffee.getCost());

        // Add sugar
        coffee = new SugarDecorator(coffee);
        System.out.println(coffee.getDescription() + " -> $" + coffee.getCost());

        // Add whipped cream
        coffee = new WhippedCreamDecorator(coffee);
        System.out.println(coffee.getDescription() + " -> $" + coffee.getCost());
    }
}
```




---

## 11. Strategy Design Pattern
> Let's consider an application used to simulate and study the interaction between robots. A robot can exhibit different behaviours such as aggressive, defensive, normal, etc. based on information provided by sensors, such as position, nearby obstacles, and other environmental factors. The robot maintains its context information, such as position and nearby obstacles.
In the main section of the application, multiple robots and behaviors are created. Each robot is assigned a unique behavior. Over time, the assigned behaviors of the robots can be changed dynamically.





```
package context;

import behavior.Behavior;

public class RobotContext {
    private String name;
    private Behavior behavior;

    public RobotContext(String name, Behavior behavior) {
        this.name = name;
        this.behavior = behavior;
    }

    public void setBehavior(Behavior behavior) {
        this.behavior = behavior;
        System.out.println(name + " has switched to a new behavior.");
    }

    public void performBehavior() {
        behavior.execute(name);
    }

    public String getName() {
        return name;
    }
}
```
```
package behavior;

public interface Behavior {
    void execute(String robotName);
}

-----------------------------------------------------------

package behavior;

public class NormalBehavior implements Behavior {
    @Override
    public void execute(String robotName) {
        System.out.println(robotName + " is behaving normally, moving casually.");
    }
}

-----------------------------------------------------------

package behavior;

public class DefensiveBehavior implements Behavior {
    @Override
    public void execute(String robotName) {
        System.out.println(robotName + " is behaving defensively, avoiding threats.");
    }
}

-----------------------------------------------------------

package behavior;

public class AggressiveBehavior implements Behavior {
    @Override
    public void execute(String robotName) {
        System.out.println(robotName + " is behaving aggressively, attacking obstacles.");
    }
}
```
```
package client;

import behavior.AggressiveBehavior;
import behavior.Behavior;
import behavior.DefensiveBehavior;
import behavior.NormalBehavior;
import context.RobotContext;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create robots with initial behaviors
        RobotContext robot1 = new RobotContext("Robot1", new AggressiveBehavior());
        RobotContext robot2 = new RobotContext("Robot2", new DefensiveBehavior());
        RobotContext robot3 = new RobotContext("Robot3", new NormalBehavior());

        // Perform initial behaviors
        robot1.performBehavior();
        robot2.performBehavior();
        robot3.performBehavior();

        // Change behaviors dynamically
        System.out.println("\nChanging behaviors dynamically...");
        robot1.setBehavior(new NormalBehavior());
        robot2.setBehavior(new AggressiveBehavior());
        robot3.setBehavior(new DefensiveBehavior());

        // Perform updated behaviors
        robot1.performBehavior();
        robot2.performBehavior();
        robot3.performBehavior();

        // Interactive example
        System.out.println("\nInteractive Example:");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter robot name: ");
        String robotName = scanner.nextLine();

        System.out.println("Choose a behavior: 1. Aggressive  2. Defensive  3. Normal");
        int choice = scanner.nextInt();

        RobotContext customRobot = new RobotContext(robotName, new NormalBehavior());
        switch (choice) {
            case 1 -> customRobot.setBehavior(new AggressiveBehavior());
            case 2 -> customRobot.setBehavior(new DefensiveBehavior());
            case 3 -> customRobot.setBehavior(new NormalBehavior());
            default -> System.out.println("Invalid choice. Keeping normal behavior.");
        }

        customRobot.performBehavior();
    }
}
```






---

## 12. Chain of Responsibility


###  **middleware**
####  **middleware/Middleware.java:** Basic validation interface
```
package refactoring_guru.chain_of_responsibility.example.middleware;

/**
 * Base middleware class.
 */
public abstract class Middleware {
    private Middleware next;

    /**
     * Builds chains of middleware objects.
     */
    public static Middleware link(Middleware first, Middleware... chain) {
        Middleware head = first;
        for (Middleware nextInChain: chain) {
            head.next = nextInChain;
            head = nextInChain;
        }
        return first;
    }

    /**
     * Subclasses will implement this method with concrete checks.
     */
    public abstract boolean check(String email, String password);

    /**
     * Runs check on the next object in chain or ends traversing if we're in
     * last object in chain.
     */
    protected boolean checkNext(String email, String password) {
        if (next == null) {
            return true;
        }
        return next.check(email, password);
    }
}
```


####  **middleware/ThrottlingMiddleware.java:** Check whether the limit on the number of requests is reached


```
package refactoring_guru.chain_of_responsibility.example.middleware;

/**
 * ConcreteHandler. Checks whether there are too many failed login requests.
 */
public class ThrottlingMiddleware extends Middleware {
    private int requestPerMinute;
    private int request;
    private long currentTime;

    public ThrottlingMiddleware(int requestPerMinute) {
        this.requestPerMinute = requestPerMinute;
        this.currentTime = System.currentTimeMillis();
    }

    /**
     * Please, not that checkNext() call can be inserted both in the beginning
     * of this method and in the end.
     *
     * This gives much more flexibility than a simple loop over all middleware
     * objects. For instance, an element of a chain can change the order of
     * checks by running its check after all other checks.
     */
    public boolean check(String email, String password) {
        if (System.currentTimeMillis() > currentTime + 60_000) {
            request = 0;
            currentTime = System.currentTimeMillis();
        }

        request++;
        
        if (request > requestPerMinute) {
            System.out.println("Request limit exceeded!");
            Thread.currentThread().stop();
        }
        return checkNext(email, password);
    }
}
```




#### **middleware/UserExistsMiddleware.java:** Check user’s credentials
```
package refactoring_guru.chain_of_responsibility.example.middleware;

import refactoring_guru.chain_of_responsibility.example.server.Server;

/**
 * ConcreteHandler. Checks whether a user with the given credentials exists.
 */
public class UserExistsMiddleware extends Middleware {
    private Server server;

    public UserExistsMiddleware(Server server) {
        this.server = server;
    }

    public boolean check(String email, String password) {
        if (!server.hasEmail(email)) {
            System.out.println("This email is not registered!");
            return false;
        }
        if (!server.isValidPassword(email, password)) {
            System.out.println("Wrong password!");
            return false;
        }
        return checkNext(email, password);
    }
}
```


####  **middleware/RoleCheckMiddleware.java:** Check user’s role
```
package refactoring_guru.chain_of_responsibility.example.middleware;

/**
 * ConcreteHandler. Checks a user's role.
 */
public class RoleCheckMiddleware extends Middleware {
    public boolean check(String email, String password) {
        if (email.equals("admin@example.com")) {
            System.out.println("Hello, admin!");
            return true;
        }
        System.out.println("Hello, user!");
        return checkNext(email, password);
    }
}
```


###  **server**
####  **server/Server.java:** Authorization target
```
package refactoring_guru.chain_of_responsibility.example.server;

import refactoring_guru.chain_of_responsibility.example.middleware.Middleware;

import java.util.HashMap;
import java.util.Map;

/**
 * Server class.
 */
public class Server {
    private Map<String, String> users = new HashMap<>();
    private Middleware middleware;

    /**
     * Client passes a chain of object to server. This improves flexibility and
     * makes testing the server class easier.
     */
    public void setMiddleware(Middleware middleware) {
        this.middleware = middleware;
    }

    /**
     * Server gets email and password from client and sends the authorization
     * request to the chain.
     */
    public boolean logIn(String email, String password) {
        if (middleware.check(email, password)) {
            System.out.println("Authorization have been successful!");

            // Do something useful here for authorized users.

            return true;
        }
        return false;
    }

    public void register(String email, String password) {
        users.put(email, password);
    }

    public boolean hasEmail(String email) {
        return users.containsKey(email);
    }

    public boolean isValidPassword(String email, String password) {
        return users.get(email).equals(password);
    }
}
```


#### **Demo.java:** Client code
```
package refactoring_guru.chain_of_responsibility.example;

import refactoring_guru.chain_of_responsibility.example.middleware.Middleware;
import refactoring_guru.chain_of_responsibility.example.middleware.RoleCheckMiddleware;
import refactoring_guru.chain_of_responsibility.example.middleware.ThrottlingMiddleware;
import refactoring_guru.chain_of_responsibility.example.middleware.UserExistsMiddleware;
import refactoring_guru.chain_of_responsibility.example.server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Demo class. Everything comes together here.
 */
public class Demo {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Server server;

    private static void init() {
        server = new Server();
        server.register("admin@example.com", "admin_pass");
        server.register("user@example.com", "user_pass");

        // All checks are linked. Client can build various chains using the same
        // components.
        Middleware middleware = Middleware.link(
            new ThrottlingMiddleware(2),
            new UserExistsMiddleware(server),
            new RoleCheckMiddleware()
        );

        // Server gets a chain from client code.
        server.setMiddleware(middleware);
    }

    public static void main(String[] args) throws IOException {
        init();

        boolean success;
        do {
            System.out.print("Enter email: ");
            String email = reader.readLine();
            System.out.print("Input password: ");
            String password = reader.readLine();
            success = server.logIn(email, password);
        } while (!success);
    }
}
```
```
Enter email: admin@example.com
Input password: admin_pass
Hello, admin!
Authorization have been successful!


Enter email: wrong@example.com
Input password: wrong_pass
This email is not registered!
Enter email: wrong@example.com
Input password: wrong_pass
This email is not registered!
Enter email: wrong@example.com
Input password: wrong_pass
Request limit exceeded!
```








---

## 13. Observer Design Pattern


