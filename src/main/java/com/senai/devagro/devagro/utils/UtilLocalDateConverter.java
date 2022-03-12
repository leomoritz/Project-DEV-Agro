package com.senai.devagro.devagro.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UtilLocalDateConverter {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static LocalDate stringToLocalDate(String dateString){
        return LocalDate.parse(dateString, dateTimeFormatter);
    }

    public static String localDateToString(LocalDate localDate){
        return localDate.format(dateTimeFormatter);
    }

}
