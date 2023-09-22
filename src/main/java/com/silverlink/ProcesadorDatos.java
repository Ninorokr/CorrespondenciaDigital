package com.silverlink;

import com.silverlink.Entidades.CanalNotificacion;
import com.silverlink.Entidades.CanalRegistro;
import com.silverlink.Entidades.Caso;
import com.silverlink.Utils.Navegador;
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
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcesadorDatos {

    String tempPath = "D:\\Temp";
    String os1Path = "D:\\002\\23\\0001\\";


    public void procesarCasos(ArrayList<Caso> casos) {
        Navegador navegador = new Navegador();
        Encarpetador encarpetador;

        for (int i = 1; i < casos.size(); i++) {
            Caso caso = casos.get(i-1);
            navegador.descargarArchivos(caso.getIdActividad());
            try {
                String item =  String.format("%04d", i);
                Path destino = Files.createDirectory(Path.of(os1Path + item));
                encarpetador = new Encarpetador(destino.toString());
                Files.walkFileTree(Path.of(tempPath), encarpetador);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

        }
    }

    public void getNroCarta(String encabezado) {
        Pattern patternNroCarta = Pattern.compile("\\d{9}");
        Matcher matcher = patternNroCarta.matcher(encabezado);
        matcher.find();
        System.out.println(matcher.group());
    }

    public void getFirmaSize(String textoCarta) {

    }

    public String getEncabezado(String textoCarta) {
        return textoCarta.substring(0, 150);
    }

    class Encarpetador extends SimpleFileVisitor<Path> {

        String destino;

        public Encarpetador (String destino) {
            this.destino = destino;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//            if(file.toString().endsWith(".pdf") || file.toString().endsWith(".PDF")) {
                Files.move(file, Path.of(destino + "\\" + file.getName(file.getNameCount()-1)));
//            }
            return super.visitFile(file, attrs);
        }
    }

}


