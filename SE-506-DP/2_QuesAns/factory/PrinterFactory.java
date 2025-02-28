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
