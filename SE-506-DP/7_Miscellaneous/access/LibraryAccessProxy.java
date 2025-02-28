package access;

import libraryitems.LibraryItem;
import user.User;

public class LibraryAccessProxy implements LibraryAccess {
    private RealLibraryAccess realLibraryAccess;

    public LibraryAccessProxy(RealLibraryAccess realLibraryAccess) {
        this.realLibraryAccess = realLibraryAccess;
    }

    @Override
    public LibraryItem accessItem(String itemID, User user) {
        if (user.hasAccess()) {
            System.out.println("Access granted to " + user.getName() + " for item " + itemID);
            return realLibraryAccess.accessItem(itemID, user);
        } else {
            System.out.println("Access denied to " + user.getName() + " for item " + itemID);
            return null;
        }
    }
}
