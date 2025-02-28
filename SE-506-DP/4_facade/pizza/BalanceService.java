package pizza;

import java.util.HashMap;
import java.util.Map;

public class BalanceService {
    private Map<String, Double> accountBalances = new HashMap<>();

    public BalanceService() {
        accountBalances.put("1234567890", 100.0); // Sample account with balance
    }

    public boolean checkBalance(String cardNumber, double amount) {
        return accountBalances.getOrDefault(cardNumber, 0.0) >= amount;
    }

    public void deductAmount(String cardNumber, double amount) {
        accountBalances.put(cardNumber, accountBalances.get(cardNumber) - amount);
        System.out.println("Amount deducted: " + amount);
    }
}
