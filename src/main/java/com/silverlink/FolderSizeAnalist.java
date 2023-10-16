package com.silverlink;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FolderSizeAnalist{

    static String start = "C:\\ProgramData\\Microsoft";

    public static void main(String[] args) throws IOException{
        Files.walkFileTree(Path.of(start), new Seeker());
    }
}

class Seeker extends SimpleFileVisitor<Path> {

    long totalFolderSizeInBytes;

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            System.out.print(dir + "\t");
            totalFolderSizeInBytes = 0L;

        return super.preVisitDirectory(dir, attrs);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        File archivo = new File(file.toString());
        totalFolderSizeInBytes += archivo.length();
        return super.visitFile(file, attrs);
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        double totalFolderSizeInMBytes = (double) totalFolderSizeInBytes / (1024L*1024L);
        System.out.println(totalFolderSizeInMBytes);
        return super.postVisitDirectory(dir, exc);
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        if (exc instanceof AccessDeniedException) {
            return FileVisitResult.SKIP_SUBTREE;
        }

        return super.visitFileFailed(file, exc);
    }
}
