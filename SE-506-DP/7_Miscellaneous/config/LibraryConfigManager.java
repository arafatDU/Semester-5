package config;

public class LibraryConfigManager {
    private static LibraryConfigManager instance;
    private double lateFee;
    private String openingHours;

    private LibraryConfigManager() {
        this.lateFee = 0.5; // Default late fee
        this.openingHours = "9:00 AM - 5:00 PM";
    }

    public static synchronized LibraryConfigManager getInstance() {
        if (instance == null) {
            instance = new LibraryConfigManager();
        }
        return instance;
    }

    public double getLateFee() {
        return lateFee;
    }

    public void setLateFee(double lateFee) {
        this.lateFee = lateFee;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }
}
