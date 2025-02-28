package factory;

import libraryitems.Book;
import libraryitems.LibraryItem;

public class BookFactory implements LibraryItemFactory {

    @Override
    public LibraryItem createLibraryItem(Object... args) {
        return new Book(args);
    }
}
