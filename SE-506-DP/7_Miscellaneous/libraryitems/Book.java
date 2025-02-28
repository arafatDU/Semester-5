package libraryitems;

public class Book implements LibraryItem {
    private String title;
    private String author;
    private String ISBN;

    public Book(Object... args) {
        this.title = args[0].toString();
        this.author = args[1].toString();
        this.ISBN = args[2].toString();
    }

    @Override
    public String getDetails() {
        return "Book: " + title + " by " + author + " (ISBN: " + ISBN + ")";
    }

    @Override
    public void borrowItem() {
        System.out.println("Borrowing Book: " + title);
    }
}
