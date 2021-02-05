package com.arabie;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathsAndFilesTest {

    public static void main(String[] args) {
        String originalPath = "d:\\data\\projects\\a-project\\..\\another-project";
        Path path = Paths.get("c:\\data\\myfile.txt");
        Path path1 = Paths.get(originalPath);

        System.out.println("path = " + path);
        System.out.println("path1 = " + path1);

        Path path2 = path1.normalize();
        System.out.println("path2 = " + path2);
        Path path3 = Paths.get("c:\\data");
        System.out.println("path3 = " + path3);
        try {
            Path newDir = Files.createDirectory(path3);
        } catch (FileAlreadyExistsException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }

        Path sourcePath = Paths.get("c:\\data\\logging.properties");
        Path destinationPath = Paths.get("c:\\data\\logging-copy.properties");
        try {
            Files.copy(sourcePath, destinationPath);
        } catch (FileAlreadyExistsException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
