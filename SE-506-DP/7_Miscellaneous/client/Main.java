package client;

import access.LibraryAccess;
import access.LibraryAccessProxy;
import access.RealLibraryAccess;
import config.LibraryConfigManager;
import factory.BookFactory;
import factory.LibraryItemFactory;
import factory.MagazineFactory;
import libraryitems.LibraryItem;
import user.User;

public class Main {
    public static void main(String[] args) {
        LibraryItemFactory bookFactory = new BookFactory();
        LibraryItemFactory magazineFactory = new MagazineFactory();

        LibraryItem book1 = bookFactory.createLibraryItem("Book", "Design Patterns", "Erich Gamma", "1234567890");
        LibraryItem magazine1 = magazineFactory.createLibraryItem("Magazine", "Tech Today", "March 2023", null);


        RealLibraryAccess realAccess = new RealLibraryAccess();
        realAccess.addItem("B1", book1);
        realAccess.addItem("M1", magazine1);


        LibraryAccess libraryProxy = new LibraryAccessProxy(realAccess);


        User user1 = new User("Alice", true);  // User with access
        User user2 = new User("Bob", false);   // User without access

        // Access items
        LibraryItem item1 = libraryProxy.accessItem("B1", user1);
        if (item1 != null) {
            item1.borrowItem();
        }

        LibraryItem item2 = libraryProxy.accessItem("M1", user2);
        if (item2 != null) {
            item2.borrowItem();
        }

        // Configure library settings
        LibraryConfigManager configManager = LibraryConfigManager.getInstance();
        System.out.println("Library Late Fee: $" + configManager.getLateFee());
        System.out.println("Library Hours: " + configManager.getOpeningHours());
    }
}
