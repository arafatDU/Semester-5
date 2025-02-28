package pizza;

public class PizzaOrderFacade {
    private AccountService accountService;
    private SecurityService securityService;
    private BalanceService balanceService;
    private LedgerService ledgerService;
    private NotificationService notificationService;

    public PizzaOrderFacade() {
        this.accountService = new AccountService();
        this.securityService = new SecurityService();
        this.balanceService = new BalanceService();
        this.ledgerService = new LedgerService();
        this.notificationService = new NotificationService();
    }

    public void orderPizza(String cardNumber, String pin, double amount) {
        if (!accountService.verifyAccount(cardNumber)) {
            System.out.println("Invalid account number.");
            return;
        }

        if (!securityService.checkPin(cardNumber, pin)) {
            System.out.println("Invalid PIN.");
            return;
        }

        if (!balanceService.checkBalance(cardNumber, amount)) {
            System.out.println("Insufficient balance.");
            return;
        }

        balanceService.deductAmount(cardNumber, amount);
        ledgerService.makeEntry(cardNumber, amount, "Pizza Order");
        notificationService.sendNotification("Your pizza order has been placed successfully!");

        System.out.println("Pizza order completed successfully!");
    }
}
