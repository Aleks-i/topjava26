package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_ID_1 = START_SEQ + 3;
    public static final int MEAL_ID_2 = START_SEQ + 4;
    public static final int MEAL_ID_3 = START_SEQ + 5;
    public static final int MEAL_ID_4 = START_SEQ + 6;
    public static final int MEAL_ID_5 = START_SEQ + 7;
    public static final int MEAL_ID_6 = START_SEQ + 8;
    public static final int MEAL_ID_7 = START_SEQ + 9;
    public static final int MEAL_ID_NOT_FOUND = 10;
    public static final int USER_MEAL_ID = MEAL_ID_2;
    public static final LocalDateTime DUPLICATE_DATE_TIME = LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0);
    public static final LocalDate START_DATE_FOR_BETWEEN = LocalDate.of(2020, Month.JANUARY, 31);
    public static final LocalDate END_DATE_FOR_BETWEEN = LocalDate.of(2020, Month.JANUARY, 31);
    public static final LocalTime START_TIME_FOR_BETWEEN = LocalTime.of(10, 0, 0);
    public static final LocalTime END_TIME_FOR_BETWEEN = LocalTime.of(13, 1, 0);

    public static final Meal MEAL_1 = new Meal(MEAL_ID_1, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
    public static final Meal MEAL_2 = new Meal(MEAL_ID_2, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000);
    public static final Meal MEAL_3 = new Meal(MEAL_ID_3, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500);
    public static final Meal MEAL_4 = new Meal(MEAL_ID_4, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100);
    public static final Meal MEAL_5 = new Meal(MEAL_ID_5, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000);
    public static final Meal MEAL_6 = new Meal(MEAL_ID_6, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500);
    public static final Meal MEAL_7 = new Meal(MEAL_ID_7, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2022, Month.JUNE, 24, 8, 0), "new meal", 1000);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(MEAL_1);
        updated.setDateTime(LocalDateTime.of(2022, Month.JUNE, 24, 11, 49));
        updated.setDescription("New Meal");
        updated.setCalories(1500);
        return updated;
    }

    public static List<Meal> getAllMealsForUser(int userId) {
        return Stream.of(MEAL_1, MEAL_2, MEAL_3, MEAL_4, MEAL_5, MEAL_6, MEAL_7)
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }

    public static List<Meal> getMealsBetween() {
        return Stream.of(MEAL_4, MEAL_5, MEAL_6, MEAL_7)
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}
