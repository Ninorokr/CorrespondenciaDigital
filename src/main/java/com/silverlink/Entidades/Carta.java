package com.silverlink.Entidades;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Carta {

    PDDocument documentoPDF;
    String texto;
    int nroCarta;
    String correoDestinatario;
    LocalDate fechaEmision;

    public Carta (PDDocument doc, String texto) {
        this.documentoPDF = doc;
        this.texto = texto;
        obtenerNroCarta();
        obtenerCorreo();
        obtenerFecha();
    }

    public PDDocument getDocumentoPDF() {
        return documentoPDF;
    }

    private void obtenerNroCarta() {
        Pattern emailPattern = Pattern.compile("(?!0)\\d{9}"); //Matches a consecutive 9-digit, but it doesn't start with 0
        Matcher matcher = emailPattern.matcher(texto);
        matcher.find();
        this.nroCarta = Integer.parseInt(matcher.group());
        System.out.println("Carta | nroCarta: " + this.nroCarta);
    }

    private void obtenerCorreo() {
        Pattern emailPattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}");
        Matcher matcher = emailPattern.matcher(texto);
        if(matcher.find()){
            this.correoDestinatario = matcher.group();
            System.out.println("Carta | correoDes: " + this.correoDestinatario);
        } else {
            System.out.println("Carta no tiene correo");
        }
    }

    private void obtenerFecha() {
//        Pattern fechaPattern = Pattern.compile("\\d{1,2}[ ].*[a-zA-Z]{4,}[ ].*[2][0]\\d\\d");
//        Pattern fechaPattern = Pattern.compile("\\b\\d{1,2}(?:\\s+de\\s+)?[a-zA-Z]+(?:\\s+de\\s+\\d{4})?(?:\\s+del\\s+\\d{4})?|\\b\\d{1,2}\\s+[a-zA-Z]+\\s+\\d{4}\\b");
        Pattern fechaPattern = Pattern.compile("[0-3]?\\d  ?(de )?[a-zA-Z]{4,} (de )?(del )?20\\d\\d");
        Matcher matcher = fechaPattern.matcher(texto);
//        System.out.println(texto);
        matcher.find();
        System.out.println(matcher.group());
        this.fechaEmision = descifrarFecha(matcher.group());
        System.out.println("Carta | fecha: " + this.fechaEmision);
    }

    private LocalDate descifrarFecha(String textoFecEmision) {
        Pattern yearPattern = Pattern.compile("20\\d\\d");
        Matcher matcher = yearPattern.matcher(textoFecEmision);
        matcher.find();
        int year = Integer.parseInt(matcher.group());

        Pattern monthPattern = Pattern.compile("[a-zA-Z]{4,}");
        matcher = monthPattern.matcher(textoFecEmision);
        matcher.find();
        String mes = matcher.group();
        int month;
        //TODO si es "setiembre" devolver 9, si es septiembre devolver 9 o el ordinal del enum (whichever is better)
        if(mes.equalsIgnoreCase("setiembre")){
            month = 9;
        } else {
            month = Mes.valueOf(mes.toLowerCase()).ordinal()+1;
        }

        Pattern dayPattern = Pattern.compile("\\d{1,2}");
        matcher = dayPattern.matcher(textoFecEmision);
        matcher.find();
        int day = Integer.parseInt(matcher.group());

        return LocalDate.of(year, month, day);
    }

    public int getNroCarta() {
        return nroCarta;
    }

    public String getCorreoDestinatario() {
        return correoDestinatario;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

}
