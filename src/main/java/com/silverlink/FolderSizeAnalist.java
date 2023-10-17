package com.silverlink;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

public class FolderSizeAnalist{

    static String start = "C:\\";

    public static void main(String[] args) throws IOException{
        Files.walkFileTree(Path.of(start), new Seeker());
    }
}

class Seeker extends SimpleFileVisitor<Path> {

    long totalFolderSizeInBytes;
    ArrayList<Carpeta> carpetas = new ArrayList<>();
    String folderName;

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            if(dir.getNameCount() < 2) {
                totalFolderSizeInBytes = 0L;
                folderName = dir.getName(dir.getNameCount()-1).toString();
            }
//            System.out.print(dir + "\t");
//            totalFolderSizeInBytes = 0L;

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
        if(dir.getNameCount() < 2) {
            double totalFolderSizeInMBytes = (double) totalFolderSizeInBytes / (1024L*1024L);
            Carpeta carpeta = new Carpeta(folderName, totalFolderSizeInMBytes);
            carpetas.add(carpeta);
            System.out.println(carpeta);
        }

        return super.postVisitDirectory(dir, exc);
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        if (exc instanceof AccessDeniedException) {
            return FileVisitResult.SKIP_SUBTREE;
        }

        return super.visitFileFailed(file, exc);
    }

    class Carpeta {
        String nomCarpeta;
        double sizeInMBytes;

        public Carpeta(String nomCarpeta, double sizeInMBytes) {
            this.nomCarpeta = nomCarpeta;
            this.sizeInMBytes = sizeInMBytes;
        }

        public String getNomCarpeta() {
            return nomCarpeta;
        }

        public void setNomCarpeta(String nomCarpeta) {
            this.nomCarpeta = nomCarpeta;
        }

        public double getSizeInMBytes() {
            return sizeInMBytes;
        }

        public void setSizeInMBytes(double sizeInMBytes) {
            this.sizeInMBytes = sizeInMBytes;
        }

        @Override
        public String toString() {
            return nomCarpeta + "\t" + String.format("%.2f", sizeInMBytes) + " MBs";
        }
    }
}
