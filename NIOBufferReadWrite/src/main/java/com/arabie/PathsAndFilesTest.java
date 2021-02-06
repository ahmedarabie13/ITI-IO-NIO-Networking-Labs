package com.arabie;

import java.io.IOException;
import java.nio.file.*;

public class PathsAndFilesTest {

    public static void main(String[] args) {
        String originalPath = "d:\\data\\projects\\a-project\\..\\another-project";
        Path path = Paths.get("c:\\data\\myfile.txt");
        Path path1 = Paths.get(originalPath);

        System.out.println("path = " + path);

        Path fileName=path.getFileName();
        System.out.println("FileName : "+ fileName);

        System.out.println("path1 = " + path1);

        Path path2 = path1.normalize();
        System.out.println("path2 = " + path2);

        Path path2Parent =path2.getParent();
        System.out.println("path2Parent = "+path2Parent);

        Path path3 = Paths.get("c:\\data");
        System.out.println("path3 = " + path3);

        Path path3Root = path3.getRoot();
        System.out.println("path3Root : "+path3Root);
        try {
            Path newDir = Files.createDirectory(path3);
        } catch (FileAlreadyExistsException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }

        Path sourcePath = Paths.get("c:\\data\\logging.properties");
        Path destinationPath = Paths.get("c:\\data\\loggingCopy.properties");
        try {
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            var lines=Files.lines(sourcePath);
            lines.forEach(System.out::println);
            System.out.println("File Copied Successfully");
        } catch (FileAlreadyExistsException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
