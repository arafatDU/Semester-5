package client;

import filesystem.File;
import filesystem.FileSystemComponent;
import filesystem.Folder;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        File file1 = new File("file1.txt", "This is the content of file1");
        File file2 = new File("file2.txt", "This is some other content");
        File file3 = new File("file3.txt", "keyword content is here");


        Folder folder1 = new Folder("Folder1");
        folder1.addComponent(file1);
        folder1.addComponent(file2);


        Folder folder2 = new Folder("Folder2");
        folder2.addComponent(file3);
        folder2.addComponent(folder1);


        Scanner scanner = new Scanner(System.in);
        file1.search("content");

        folder1.search("content");
    }


}
