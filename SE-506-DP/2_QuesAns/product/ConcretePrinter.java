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