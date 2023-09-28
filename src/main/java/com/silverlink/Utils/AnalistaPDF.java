package com.silverlink.Utils;

import com.silverlink.Entidades.Acta;
import com.silverlink.Entidades.Carta;
import com.silverlink.Entidades.Caso;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalistaPDF {

    public void reconocerActaOCarta(ArrayList<PDDocument> documentos, Caso caso) {
        //Reconocer qué documento es carta y cuál es acta y agregarlo a su respectiva colección
        for (PDDocument doc : documentos) {
            try {
                PDFTextStripper stripper = new PDFTextStripper();
                String texto = stripper.getText(doc);
                Pattern patternActa = Pattern.compile("Acta de Comunicación", Pattern.CASE_INSENSITIVE);
                Matcher matcher = patternActa.matcher(texto.substring(0, 30));

                if (matcher.find()) {
                    System.out.println(matcher.group());
                    caso.getActas().add(new Acta(doc, texto));
                } else {
                    caso.getCartas().add(new Carta(doc, texto));
                }
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        }
    }

}
