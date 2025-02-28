package factory;

import libraryitems.LibraryItem;
import libraryitems.Magazine;

public class MagazineFactory implements LibraryItemFactory {

    @Override
    public LibraryItem createLibraryItem(Object... args) {
        return new Magazine(args);
    }
}
