package access;

import libraryitems.LibraryItem;
import user.User;

public interface LibraryAccess {
    LibraryItem accessItem(String itemID, User user);
}
