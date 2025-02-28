package pizza;

public class AccountService {
    public boolean verifyAccount(String cardNumber) {
        // Simulate account verification (in a real system, this would query a database or API)
        return cardNumber != null && !cardNumber.isEmpty();
    }
}
