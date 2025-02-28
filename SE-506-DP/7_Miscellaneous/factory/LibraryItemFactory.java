package factory;

import libraryitems.Book;
import libraryitems.LibraryItem;
import libraryitems.Magazine;

public interface LibraryItemFactory {
    public LibraryItem createLibraryItem(Object... args);
}
