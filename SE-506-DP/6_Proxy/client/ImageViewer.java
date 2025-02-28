package client;

import image.Image;
import image.ImageProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ImageViewer {
    private List<Image> images;

    public ImageViewer() {
        images = new ArrayList<>();
        loadImages();
    }

    private void loadImages() {
        images.add(new ImageProxy("Beach.jpg"));
        images.add(new ImageProxy("Mountain.jpg"));
        images.add(new ImageProxy("Cityscape.jpg"));
        images.add(new ImageProxy("Desert.jpg"));
    }

    public void displayImageList() {
        System.out.println("Image List:");
        for (int i = 0; i < images.size(); i++) {
            System.out.println((i + 1) + ". " + images.get(i).getFilename());
        }
    }

    public void viewImage(int index) {
        if (index >= 1 && index <= images.size()) {
            Image image = images.get(index - 1);
            image.display();
        } else {
            System.out.println("Invalid image selection.");
        }
    }

    public static void main(String[] args) {
        ImageViewer imageViewer = new ImageViewer();
        imageViewer.displayImageList();

        Scanner scanner = new Scanner(System.in);


        while(true) {
            System.out.print("Select an image to view (enter number): ");
            int choice = scanner.nextInt();
            imageViewer.viewImage(choice);

            if(choice == 0) break;
        }

    }
}
