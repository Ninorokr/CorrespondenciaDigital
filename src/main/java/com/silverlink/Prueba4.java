package com.silverlink;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Date;
import java.util.Locale;

public class Prueba4 {

    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy KK:mm a");
        String formattedDate = dateFormat
                .format(Timestamp
                        .valueOf(LocalDateTime
                                .of(2023,10,12, 18, 45, 15)));
        System.out.println(formattedDate);
    }
}
