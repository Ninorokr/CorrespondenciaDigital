package com.silverlink;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class Prueba3 {

    public static void main(String[] args) throws IOException {

        Files.walkFileTree(Path.of("D:\\prueba"), new SimpleFileVisitor<>(){
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (!(file.toString().endsWith(".crdownload") || file.toString().endsWith(".tmp") ||
                        (file.toString().endsWith(".CRDOWNLOAD") || file.toString().endsWith(".TMP"))))
                    System.out.println(file);
                return super.visitFile(file, attrs);
            }
        });

    }


}
