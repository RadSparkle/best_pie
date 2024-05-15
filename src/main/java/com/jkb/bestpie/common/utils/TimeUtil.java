package com.jkb.bestpie.common.utils;


import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class TimeUtil {

    private final String YYYYMMDDHHMMSS_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public LocalDateTime getLocalDateTime(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(YYYYMMDDHHMMSS_FORMAT));
    }
}
