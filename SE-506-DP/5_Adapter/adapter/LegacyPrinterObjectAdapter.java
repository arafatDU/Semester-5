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