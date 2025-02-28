package filesystem;

import java.util.ArrayList;
import java.util.List;

public class Folder implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> components;

    public Folder(String name) {
        this.name = name;
        this.components = new ArrayList<>();
    }

    public void addComponent(FileSystemComponent component) {
        components.add(component);
    }

    public void removeComponent(FileSystemComponent component) {
        components.remove(component);
    }

    public List<FileSystemComponent> getComponents() {
        return components;
    }

    @Override
    public boolean search(String keyword) {
        System.out.println("Searching in Folder: " + name);
        boolean found = false;

        for (FileSystemComponent component : components) {
            if (component.search(keyword)) {
                found = true;
            }
        }

        if (!found) {
            System.out.println("Keyword not found in Folder: " + name);
        }
        return found;
    }

    @Override
    public String toString() {
        return "Folder: " + name;
    }
}
