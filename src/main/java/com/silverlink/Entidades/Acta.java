package com.silverlink.Entidades;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Acta {

    PDDocument documentoPDF;
    String texto;
    int nroCarta;
    String correoDestinatario;
    LocalDateTime fechaEntrega;

    public Acta (PDDocument pdf) {
        this.documentoPDF = pdf;
        this.texto = getTexto();
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
        matcher.find(); matcher.find(); //Para ubicar la segunda coincidencia
        this.nroCarta = Integer.parseInt(matcher.group());
        System.out.println(this.nroCarta);
    }

    private void obtenerCorreo() {
        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$");
        Matcher matcher = emailPattern.matcher(texto);
        matcher.find(); matcher.find(); //Para ubicar la segunda coincidencia
        this.correoDestinatario = matcher.group();
        System.out.println(this.correoDestinatario);
    }

    private void obtenerFecha() {
        Pattern fechaPattern = Pattern.compile("[0-9]{4}-[a-z]{3}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}");
        Matcher matcher = fechaPattern.matcher(texto);
        matcher.find(); matcher.find(); //Para ubicar la segunda coincidencia
        System.out.println(matcher.group());
        this.fechaEntrega = descifrarFechaHora(matcher.group());
    }

    private LocalDateTime descifrarFechaHora(String texto) {
        int year = Integer.parseInt(texto.substring(0, 4));
        int month = MesAbr.valueOf(texto.substring(5, 8)).ordinal()+1;
        int day = Integer.parseInt(texto.substring(9, 11));
        LocalDate date = LocalDate.of(year, month, day);

        int hour = Integer.parseInt(texto.substring(12, 14));
        int minute = Integer.parseInt(texto.substring(15, 17));
        int second = Integer.parseInt(texto.substring(18, 20));
        LocalTime time = LocalTime.of(hour, minute, second);

        return LocalDateTime.of(date, time);
    }



}
