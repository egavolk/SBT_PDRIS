package edu.phystech.pdris.common.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Util {
    private Util() {}

    public static String getStringDateForPattern(Instant date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern)
                .withZone(ZoneId.systemDefault());
        return formatter.format(date);
    }
}
