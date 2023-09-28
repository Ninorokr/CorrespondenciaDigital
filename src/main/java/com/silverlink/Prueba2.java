package com.silverlink;

import com.silverlink.Entidades.MesAbrv;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Prueba2 {

    public static void main(String[] args) {

        String text1 = "18 de septiembre del 2023";
        String text2 = "18 septiembre 2023";
        String text3 = "1 Lima, 18 de septiembre del 2023";
        String regex = "\\b\\d{1,2}(?:\\s+de\\s+)?[a-zA-Z]+(?:\\s+del\\s+\\d{4})?\\b|\\b\\d{1,2}\\s+[a-zA-Z]+\\s+\\d{4}\\b";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher1 = pattern.matcher(text1);
        while (matcher1.find()) {
            System.out.println("Match in text1: " + matcher1.group());
        }

        Matcher matcher2 = pattern.matcher(text2);
        while (matcher2.find()) {
            System.out.println("Match in text2: " + matcher2.group());
        }

        Matcher matcher3 = pattern.matcher(text3);
        while (matcher3.find()) {
            System.out.println("Match in text3: " + matcher3.group());
        }

//        String sDate1="31/12/1998";
//        String sDate2 = "31-Dec-1998";
//        String sDate3 = "12 31, 1998";
//        String sDate4 = "Thu, Dec 31 1998";
//        String sDate5 = "Thu, Dec 31 1998 23:37:50";
//        String sDate6 = "31-Dec-1998 23:37:50";
//        SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");
//        SimpleDateFormat formatter2=new SimpleDateFormat("dd-MMM-yyyy");
//        SimpleDateFormat formatter3=new SimpleDateFormat("MM dd, yyyy");
//        DateTimeFormatter formatter4 = new DateTimeFormatter("EEE, MMM dd yyyy");
//        SimpleDateFormat formatter5=new SimpleDateFormat("E, MMM dd yyyy HH:mm:ss");
//        SimpleDateFormat formatter6=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
//        Date date1=formatter1.parse(sDate1);
//        Date date2=formatter2.parse(sDate2);
//        Date date3=formatter3.parse(sDate3);
//        LocalDateTime date4=formatter4.parse(sDate4);
//        Date date5=formatter5.parse(sDate5);
//        Date date6=formatter6.parse(sDate6);
//        System.out.println(sDate1+"\t"+date1);
//        System.out.println(sDate2+"\t"+date2);
//        System.out.println(sDate3+"\t"+date3);
//        System.out.println(sDate4+"\t"+date4);
//        System.out.println(sDate5+"\t"+date5);
//        System.out.println(sDate6+"\t"+date6);

//        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEE, MMM dd yyyy");
//        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//        String sDate5 = "Jue, Dic 31 1998";
//        String sTime5 = "23:37:50";
//        LocalDate date = LocalDate.of(2023, 5, 12);
//        LocalTime time = LocalTime.parse(sTime5, timeFormatter);
//        LocalDateTime dateTime = date.atTime(time);
//
//        System.out.println(dateTime);

//        String fechaYHoraEnTexto = "2023-sep-22 21:38:04";
//        System.out.println(descifrarFechaHora(fechaYHoraEnTexto));
//    }
//
//    public static LocalDateTime descifrarFechaHora(String texto) {
//        int year = Integer.parseInt(texto.substring(0, 4));
//        int month = MesAbrv.valueOf(texto.substring(5, 8)).ordinal()+1;
//        int day = Integer.parseInt(texto.substring(9, 11));
//        LocalDate date = LocalDate.of(year, month, day);
//
//        int hour = Integer.parseInt(texto.substring(12, 14));
//        int minute = Integer.parseInt(texto.substring(15, 17));
//        int second = Integer.parseInt(texto.substring(18, 20));
//        LocalTime time = LocalTime.of(hour, minute, second);
//
//        return LocalDateTime.of(date, time);
//    }
    }
}
