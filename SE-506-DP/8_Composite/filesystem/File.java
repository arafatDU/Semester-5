package filesystem;

public class File implements FileSystemComponent {
    private String name;
    private String content;

    public File(String name, String content) {
        this.name = name;
        this.content = content;
    }

    @Override
    public boolean search(String keyword) {
        if (content.contains(keyword)) {
            System.out.println("Keyword found in File: " + name);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "File: " + name;
    }
}
