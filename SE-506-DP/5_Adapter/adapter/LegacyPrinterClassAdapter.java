package adapter;

public class LegacyPrinterClassAdapter extends LegacyPrinter implements PrinterClientInterface {
    @Override
    public void printDocument(String content) {
        // Directly calling the inherited method from LegacyPrinter
        printOldWay(content);
    }
}