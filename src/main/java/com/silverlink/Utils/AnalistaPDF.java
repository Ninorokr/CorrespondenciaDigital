package com.silverlink.Utils;

import com.silverlink.Entidades.Acta;
import com.silverlink.Entidades.Carta;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalistaPDF {

    ArrayList<Carta> cartas;
    ArrayList<Acta> actas;

    public void reconocerPDFs(ArrayList<PDDocument> documentos) {
        //Reconocer qué documento es carta y cuál es acta y agregarlo a su respectiva colección
        for (PDDocument doc : documentos) {
            try {
                PDFTextStripper stripper = new PDFTextStripper();

                Pattern patternActa = Pattern.compile("Acta de Comunicación", Pattern.CASE_INSENSITIVE);
                Matcher matcher = patternActa.matcher(stripper.getText(doc).substring(0, 30));

                if (matcher.find()) {
                    System.out.println(matcher.group());
                    actas.add(new Acta(doc));
                } else {
                    cartas.add(new Carta(doc));
                }
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        }
    }

}
