package pizza;

public class SecurityService {
    public boolean checkPin(String cardNumber, String pin) {
        // Simulate PIN check (in a real system, this would query a secure database)
        return pin.equals("1234"); // For simplicity, we assume PIN "1234" is valid
    }
}
