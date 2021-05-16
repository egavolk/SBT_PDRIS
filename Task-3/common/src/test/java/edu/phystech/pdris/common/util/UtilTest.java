package edu.phystech.pdris.common.util;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.Assert.assertEquals;

class UtilTest {

    @Test
    void getStringDateForPattern() {
        Instant instant = Instant.parse("2020-09-08" +
                "T10:15:30.00Z");
        assertEquals("2020-09-08",
                Util.getStringDateForPattern(instant, "yyyy-MM-dd"));
        assertEquals("2020/08/09",
                Util.getStringDateForPattern(instant, "yyyy/dd/MM"));
    }
}