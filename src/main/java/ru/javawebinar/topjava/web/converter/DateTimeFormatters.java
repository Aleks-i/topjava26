package ru.javawebinar.topjava.web.converter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

public class DateTimeFormatters {
    public static class DateFormatter implements Formatter<LocalDate> {

        @Override
        public LocalDate parse(String ld, Locale locale) throws ParseException {
            return parseLocalDate(ld);
        }

        @Override
        public String print(LocalDate ld, Locale locale) {
            return ld.format(DateTimeFormatter.ISO_DATE);
        }
    }

    public static class TimeFormatter implements Formatter<LocalTime> {

        @Override
        public LocalTime parse(String lt, Locale locale) throws ParseException {
            return parseLocalTime(lt);
        }

        @Override
        public String print(LocalTime lt, Locale locale) {
            return lt.format(DateTimeFormatter.ISO_TIME);
        }
    }
}