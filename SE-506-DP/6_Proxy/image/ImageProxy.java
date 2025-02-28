package image;

public class ImageProxy implements Image {
    private String filename;
    private RealImage realImage;

    public ImageProxy(String filename) {
        this.filename = filename;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename); // Load real image only when display() is called
        }
        else {
            System.out.println("Loaded From Cached: " + filename);
        }
        realImage.display();
    }

    @Override
    public String getFilename() {
        return filename;
    }
}
