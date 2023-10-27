package com.silverlink.Utils;

import com.silverlink.Entidades.Acta;
import com.silverlink.Entidades.Carta;
import com.silverlink.Entidades.Caso;
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
import org.apache.pdfbox.text.PDFTextStripper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.silverlink.Main.rootFolder;

public class AnalistaPDF {

//    public static void reconocerActaOCarta(ArrayList<PDDocument> documentos, Caso caso) {
//        //Reconocer qué documento es carta y cuál es acta y agregarlo a su respectiva colección
//        for (PDDocument doc : documentos) {
//            try {
//                PDFTextStripper stripper = new PDFTextStripper();
//                String texto = stripper.getText(doc);
//                Pattern patternActa = Pattern.compile("Acta de Comunicación", Pattern.CASE_INSENSITIVE);
//                Matcher matcher = patternActa.matcher(texto.substring(0, 30));
//
//                if (matcher.find()) {
//                    System.out.println(matcher.group());
//                    caso.getActas().add(new Acta(texto));
//                } else {
//                    caso.getCartas().add(new Carta(texto));
//                }
//            } catch (IOException ioe) {
//                System.out.println(ioe.getMessage());
//            }
//        }
//    }

    public static void reconocerActaOCarta(File file, Caso caso) {
        //Reconocer qué documento es carta y cuál es acta y agregarlo a su respectiva colección

        try (PDDocument doc = Loader.loadPDF(file)) {
            PDFTextStripper stripper = new PDFTextStripper();
            String texto = stripper.getText(doc);
            Pattern patternActa = Pattern.compile("Acta de Comunicación", Pattern.CASE_INSENSITIVE);
            Matcher matcher = patternActa.matcher(texto.substring(0, 30));

            if (matcher.find()) {
//                System.out.println(matcher.group());
                caso.getActas().add(new Acta(doc, texto, caso.getCorreosActas()));
            } else {
                caso.getCartas().add(new Carta(doc, texto, caso.getCorreosCartas()));
                SaveImagesInPdf printer;
                for(PDPage page : doc.getPages()) {
                    printer = new SaveImagesInPdf(caso);
                    printer.processPage(page);
                }
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

}

class SaveImagesInPdf extends PDFStreamEngine {

    //        public int docNum;
    //        public int pageNum;
    public int imageNumber = 1;
    Caso caso;

    SaveImagesInPdf (Caso caso) {
//            this.docNum = docNum;
//            this.pageNum = pageNum;
        this.caso = caso;
    }

    @Override
    public void processOperator(Operator operator, List<COSBase> operands) throws IOException {
        String operation = operator.getName();
        if ("Do".equals(operation)) {
            COSName objectName = (COSName) operands.get(0);
            PDXObject xobject = getResources().getXObject(objectName);
            if (xobject instanceof PDImageXObject) {
                PDImageXObject image = (PDImageXObject) xobject;

                // same image to local
                BufferedImage bImage = image.getImage();
                String nroOS = String.format("%04d", caso.getNroOS());
                String nroID = String.format("%04d", caso.getIdCaso());
                String exportPath = rootFolder + caso.getAnio() + "\\" + nroOS + "\\" + nroID + "\\";

                DataBuffer dataBuffer = bImage.getData().getDataBuffer();

                // Each bank element in the data buffer is a 32-bit integer
                long sizeBytes = ((long) dataBuffer.getSize()) * 4L;
                long sizeKB = sizeBytes / (1024L);
//                long sizeMB = sizeBytes / (1024L * 1024L);

//                System.out.println(imageNumber + ", size in: ");
//                System.out.println("Bytes: " + sizeBytes + " bytes");
//                System.out.println("MBytes: " + sizeKB + " KBs");
                //TAMAÑO MÍNIMO: 7KB
                //TAMAÑO MÁXIMO: 120KB
                ImageIO.write(bImage,"PNG", new File(exportPath + "firma_" + caso.getIdCaso() +
                        "_" + imageNumber + ".png"));
//                System.out.println("Image saved.");
                imageNumber++;
            }
            else if(xobject instanceof PDFormXObject) {
                PDFormXObject form = (PDFormXObject)xobject;
                showForm(form);
            }
        }
        else {
            super.processOperator(operator, operands);
        }

    }
}
