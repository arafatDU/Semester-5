package libraryitems;

public class Magazine implements LibraryItem {
    private String title;
    private String issue;

    public Magazine(Object... args) {
        this.title = args[0].toString();
        this.issue = args[1].toString();
    }

    @Override
    public String getDetails() {
        return "Magazine: " + title + " (Issue: " + issue + ")";
    }

    @Override
    public void borrowItem() {
        System.out.println("Borrowing Magazine: " + title);
    }
}
