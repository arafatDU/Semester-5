package pizza;

public class LedgerService {
    public void makeEntry(String cardNumber, double amount, String operation) {
        System.out.println("Ledger entry made for card: " + cardNumber + ", amount: " + amount + ", operation: " + operation);
    }
}
