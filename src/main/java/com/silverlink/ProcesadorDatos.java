package com.silverlink;

import com.silverlink.Entidades.CanalNotificacion;
import com.silverlink.Entidades.CanalRegistro;
import com.silverlink.Entidades.Caso;
import org.apache.commons.io.IOUtils;
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
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcesadorDatos {

//    String cartaPath = "Z:\\Servicios ENEL\\002 - Correspondencia digital\\Temp\\Carta N° 493465352-1 (2).pdf";
//    String acta = "Z:\\Servicios ENEL\\002 - Correspondencia digital\\Temp\\communicationAct (7) (7).pdf";
//
//
//    public void start() {
////        ArrayList<Caso> casosPendientes = new ArrayList<>();
//
//        try (PDDocument cartaPDF = Loader.loadPDF(new File(cartaPath))){
////            String textoCarta = new PDFTextStripper().getText(cartaPDF);
////            String encabezado = getEncabezado(textoCarta);
//            SaveImagesInPdf printer = new SaveImagesInPdf();
//            int pageNum = 0;
//            for( PDPage page : cartaPDF.getPages() )
//            {
//                pageNum++;
//                System.out.println( "Processing page: " + pageNum );
//                printer.processPage(page);
//            }
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//
//    }
//
//    public void getNroCarta(String encabezado) {
//        Pattern patternNroCarta = Pattern.compile("\\d{9}");
//        Matcher matcher = patternNroCarta.matcher(encabezado);
//        matcher.find();
//        System.out.println(matcher.group());
//    }
//
//    public void getFirmaSize(String textoCarta) {
//
//    }
//
//    public String getEncabezado(String textoCarta) {
//        return textoCarta.substring(0, 150);
//    }
//
//}

//class SaveImagesInPdf extends PDFStreamEngine {
//
//    public int imageNumber = 1;
//
//    @Override
//    public void processOperator(Operator operator, List<COSBase> operands) throws IOException {
//        String operation = operator.getName();
//        if( "Do".equals(operation) )
//        {
//            COSName objectName = (COSName) operands.get( 0 );
//            PDXObject xobject = getResources().getXObject( objectName );
//            if( xobject instanceof PDImageXObject)
//            {
//                PDImageXObject image = (PDImageXObject)xobject;
//
//                // same image to local
//                BufferedImage bImage = image.getImage();
//                ImageIO.write(bImage,"PNG", new File("D:\\imagenesExtraidasPDF\\image_"+imageNumber+".png"));
//                System.out.println("Image saved.");
//                imageNumber++;
//
//            }
//            else if(xobject instanceof PDFormXObject)
//            {
//                PDFormXObject form = (PDFormXObject)xobject;
//                showForm(form);
//            }
//        }
//        else
//        {
//            super.processOperator( operator, operands);
//        }
//    }
}


