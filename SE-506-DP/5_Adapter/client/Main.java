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
