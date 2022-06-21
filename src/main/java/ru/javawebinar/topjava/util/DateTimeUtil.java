package ru.javawebinar.topjava.util;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final LocalDate MIN_DATE = LocalDate.of(1, 1, 1);
    private static final LocalDate MAX_DATE = LocalDate.of(5000, 1, 1);

    public static boolean isBetweenHalfOpen(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return (startTime == null || lt.compareTo(startTime) >= 0) && (endTime == null || lt.compareTo(endTime) < 0);
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

    public static LocalDate parseLocalDate(String str) {
        return StringUtils.hasLength(str) ? LocalDate.parse(str) : null;
    }

    public static LocalTime parseLocalTime(String str) {
        return StringUtils.hasLength(str) ? LocalTime.parse(str) : null;
    }

    public static LocalDate getStartDateOrMin(@Nullable LocalDate localDate) {
        return localDate == null ? MIN_DATE : localDate.minusDays(1);
    }

    public static LocalDate getEndDateOrMax(@Nullable LocalDate localDate) {
        return localDate == null ? MAX_DATE : localDate.plusDays(1);
    }
}