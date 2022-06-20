package ru.javawebinar.topjava.web;

import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

public class SecurityUtil {

    private static int id = 1;
    public static final int USER_ID = 1;
    public static final int ADMIN_ID = 2;

    public static int authUserId() {
        return id;
    }

    public static void setAuthUserId(int userId) {
        id = userId;
    }

    public static int authUserCaloriesPerDay() {
        return DEFAULT_CALORIES_PER_DAY;
    }
}