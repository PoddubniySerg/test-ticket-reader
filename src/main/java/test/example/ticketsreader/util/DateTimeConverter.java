package test.example.ticketsreader.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateTimeConverter {

    private final static String DATE_TIME_FORMAT = "yy.MM.dd HH:mm";

    public long stringToLong(String date, String time) {
        final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        if (time.length() < 5) {
            time = String.format("0%s", time);
        }
        final LocalDateTime dateTime = LocalDateTime.parse(String.format("%s %s", date, time), dateFormatter);
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
