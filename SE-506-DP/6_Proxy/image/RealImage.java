package image;

public class RealImage implements Image {
    private String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadImageFromDisk(); // Load image upon creation
    }

    private void loadImageFromDisk() {
        System.out.println("Loading high-resolution image: " + filename);
    }

    @Override
    public void display() {
        System.out.println("Displaying high-resolution image: " + filename);
    }

    @Override
    public String getFilename() {
        return filename;
    }
}
