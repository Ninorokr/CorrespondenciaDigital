package com.silverlink.Entidades;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.IOException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Carta {

    PDDocument documentoPDF;
    String texto;
    int nroCarta;
    String correoDestinatario;
    LocalDate fechaEntrega;

    public Carta (PDDocument pdf) {
        this.documentoPDF = pdf;
        obtenerNroCarta();
        obtenerCorreo();
        obtenerFecha();
    }

    private String getTexto() {
        try {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(documentoPDF);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        return null;
    }

    private void obtenerNroCarta() {
        Pattern emailPattern = Pattern.compile("(?!0)\\d{9}"); //Matches a consecutive 9-digit, but it doesn't start with 0
        Matcher matcher = emailPattern.matcher(texto);
        matcher.find();
        this.nroCarta = Integer.parseInt(matcher.group());
        System.out.println(this.nroCarta);
    }

    private void obtenerCorreo() {
        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$");
        Matcher matcher = emailPattern.matcher(texto);
        matcher.find();
        this.correoDestinatario = matcher.group();
        System.out.println(this.correoDestinatario);
    }

    private void obtenerFecha() {
        Pattern fechaPattern = Pattern.compile("\\d{1,2}[ ].*[a-z]{4,}[ ].*[2][0]\\d\\d");
        Matcher matcher = fechaPattern.matcher(texto);
        matcher.find();
        this.fechaEntrega = descifrarFecha(matcher.group());
        System.out.println(this.fechaEntrega);
    }

    private LocalDate descifrarFecha(String textoFecEmision) {
        Pattern yearPattern = Pattern.compile("[2][0]\\d\\d");
        Matcher matcher = yearPattern.matcher(textoFecEmision);
        matcher.find();
        int year = Integer.parseInt(matcher.group());

        Pattern monthPattern = Pattern.compile("[a-z]{4,}");
        matcher = monthPattern.matcher(textoFecEmision);
        matcher.find();
        //TODO si es "setiembre" devolver 7, si es septiembre devolver 7 o el ordinal del enum (whichever is better)
        int month = Mes.valueOf(textoFecEmision.substring(5, 8)).ordinal()+1;

        Pattern dayPattern = Pattern.compile("\\d{1,2}");
        matcher = dayPattern.matcher(textoFecEmision);
        matcher.find();
        int day = Integer.parseInt(matcher.group());

        return LocalDate.of(year, month, day);
    }

    public void getFirmaSize(String textoCarta) {

    }
}
