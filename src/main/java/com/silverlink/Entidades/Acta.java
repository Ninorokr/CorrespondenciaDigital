package com.silverlink.Entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Acta {

//    PDDocument documentoPDF;
    String texto;
    int nroCarta;
    String correoDestinatario;
    LocalDateTime fechaEntrega;

    public Acta (String texto) {
//        this.documentoPDF = pdf;
        this.texto = texto;
        obtenerNroCarta();
        obtenerCorreo();
        obtenerFecha();
    }

    private int obtenerNroCarta() {
        Pattern emailPattern = Pattern.compile("(?!0)\\d{9}"); //Matches a consecutive 9-digit, but it doesn't start with 0
        Matcher matcher = emailPattern.matcher(texto);
        matcher.find();
        this.nroCarta = Integer.parseInt(matcher.group());
        System.out.println("Acta | nroCarta: " + this.nroCarta);
        return this.nroCarta;
    }

    private String obtenerCorreo() {
        Pattern emailPattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}");
        Matcher matcher = emailPattern.matcher(texto);
        matcher.find(); matcher.find(); //Para ubicar la segunda coincidencia
        this.correoDestinatario = matcher.group();
        System.out.println("Acta | correoDes: " + this.correoDestinatario);
        return this.correoDestinatario;
    }

    private LocalDateTime obtenerFecha() {
        Pattern fechaPattern = Pattern.compile("[0-9]{4}-[a-z]{3}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}");
        Matcher matcher = fechaPattern.matcher(texto);
        matcher.find(); matcher.find(); //Para ubicar la segunda coincidencia
        this.fechaEntrega =  descifrarFechaHora(matcher.group());
        System.out.println("Acta | fecha: " + this.fechaEntrega);
        return this.fechaEntrega;
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



}
