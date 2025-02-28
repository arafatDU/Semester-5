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
