package adapter;

public class ModernPrinter implements PrinterClientInterface{
    @Override
    public void printDocument(String content) {
        System.out.println("Modern Printer: Printing -> " + content);
    }
}
