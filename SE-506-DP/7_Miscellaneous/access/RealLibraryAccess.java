package access;

import libraryitems.LibraryItem;
import user.User;
import java.util.HashMap;
import java.util.Map;

public class RealLibraryAccess implements LibraryAccess {
    private Map<String, LibraryItem> libraryItems = new HashMap<>();

    public void addItem(String itemID, LibraryItem item) {
        libraryItems.put(itemID, item);
    }

    @Override
    public LibraryItem accessItem(String itemID, User user) {
        return libraryItems.get(itemID);
    }
}
