package com.silverlink.Entidades;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Acta {

    PDDocument documentoPDF;
    String texto;
    int nroActa;
    String correoDestinatario;
    LocalDateTime fechaEntrega;

    public Acta (PDDocument doc, String texto) {
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
        Pattern palabraConstancia = Pattern.compile("Constancia");
        Matcher matcher = palabraConstancia.matcher(texto);
        if(!matcher.find()){
            palabraConstancia = Pattern.compile("Documentos");
            matcher = palabraConstancia.matcher(texto);
        }
        int searchLimit = matcher.start();

        Pattern emailPattern = Pattern.compile("(?!0)\\d{9}"); //Matches a consecutive 9-digit, but it doesn't start with 0
        matcher = emailPattern.matcher(texto.substring(0, searchLimit));
        matcher.find();
        this.nroActa = Integer.parseInt(matcher.group());
        System.out.println("Acta | nroCarta: " + this.nroActa);
//        return this.nroCarta;
    }

    private void obtenerCorreo() {
        Pattern emailPattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}");
        Matcher matcher = emailPattern.matcher(texto);
        matcher.find(); matcher.find(); //Para ubicar la segunda coincidencia
        this.correoDestinatario = matcher.group();
        System.out.println("Acta | correoDes: " + this.correoDestinatario);
//        return this.correoDestinatario;
    }

    private void obtenerFecha() {
        Pattern fechaPattern = Pattern.compile("[0-9]{4}-[a-z]{3}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}");
        Matcher matcher = fechaPattern.matcher(texto);
        matcher.find(); matcher.find(); //Para ubicar la segunda coincidencia
        this.fechaEntrega =  descifrarFechaHora(matcher.group());
        System.out.println("Acta | fecha: " + this.fechaEntrega);
//        return this.fechaEntrega;
    }

    private LocalDateTime descifrarFechaHora(String texto) {
        int year = Integer.parseInt(texto.substring(0, 4));
        int month = MesAbrv.valueOf(texto.substring(5, 8)).ordinal()+1;
        int day = Integer.parseInt(texto.substring(9, 11));
        LocalDate date = LocalDate.of(year, month, day);

        int hour = Integer.parseInt(texto.substring(12, 14));
        int minute = Integer.parseInt(texto.substring(15, 17));
        int second = Integer.parseInt(texto.substring(18, 20));
        LocalTime time = LocalTime.of(hour, minute, second);

        return LocalDateTime.of(date, time);
    }

    public int getNroActa() {
        return nroActa;
    }

    public String getCorreoDestinatario() {
        return correoDestinatario;
    }

    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }
}
