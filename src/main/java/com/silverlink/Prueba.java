package com.silverlink;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.contentstream.PDFStreamEngine;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class Prueba {

    static String cartasPath = "D:\\002\\23\\0001";

    public static void main(String[] args) {

        try {
            Files.walkFileTree(Path.of(cartasPath), new Walker());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}

class Walker extends SimpleFileVisitor<Path> {

    int pageNum;
    int docNum = 1;

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        try (PDDocument cartaPDF = Loader.loadPDF(file.toFile())){
            pageNum = 1;
            SaveImagesInPdf printer;
            for(PDPage page : cartaPDF.getPages()) {
                printer = new SaveImagesInPdf(docNum, pageNum);
                System.out.println("Processing page: " + pageNum);
                pageNum++;
                printer.processPage(page);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        docNum++;
        return super.visitFile(file, attrs);
    }
}

class SaveImagesInPdf extends PDFStreamEngine {

    public int docNum;
    public int pageNum;
    public int imageNumber = 1;

    SaveImagesInPdf (int docNum, int pageNum) {
        this.docNum = docNum;
        this.pageNum = pageNum;
    }

    @Override
    public void processOperator(Operator operator, List<COSBase> operands) throws IOException {
        String operation = operator.getName();
        if("Do".equals(operation))
        {
            COSName objectName = (COSName) operands.get(0);
            PDXObject xobject = getResources().getXObject(objectName);
            if(xobject instanceof PDImageXObject)
            {
                PDImageXObject image = (PDImageXObject)xobject;

                // same image to local
                BufferedImage bImage = image.getImage();
                ImageIO.write(bImage,"PNG", new File("D:\\imagenesExtraidasPDF\\image_"+docNum+"_"+pageNum+"_"+imageNumber+".png"));
//                System.out.println("Image saved.");
                imageNumber++;

            }
            else if(xobject instanceof PDFormXObject)
            {
                PDFormXObject form = (PDFormXObject)xobject;
                showForm(form);
            }
        }
        else
        {
            super.processOperator( operator, operands);
        }
    }
}