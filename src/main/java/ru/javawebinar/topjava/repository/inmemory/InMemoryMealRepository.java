package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.MealsUtil.mealsAdmin;
import static ru.javawebinar.topjava.util.MealsUtil.mealsUser;
import static ru.javawebinar.topjava.web.SecurityUtil.ADMIN_ID;
import static ru.javawebinar.topjava.web.SecurityUtil.USER_ID;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        mealsUser.forEach(m -> save(m, USER_ID));
        mealsAdmin.forEach(m -> save(m, ADMIN_ID));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        repository.putIfAbsent(userId, new ConcurrentHashMap<>());
        if (meal.isNew()) {
            int mealId = counter.incrementAndGet();
            meal.setId(mealId);
            return repository.get(userId).put(mealId, meal);
        }
        // handle case: update, but not present in storage
        return repository.get(userId).computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        return repository.get(userId).remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        return repository.get(userId).get(id);
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        Map<Integer, Meal> meals = repository.get(userId);
        return meals == null ? Collections.emptyList() : meals.values().stream()
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> getAllBetweenDate(LocalDate startDate, LocalDate endDate, int userId) {
        Map<Integer, Meal> mealsBetweenDate = repository.get(userId);
        return mealsBetweenDate == null ? Collections.emptyList() : mealsBetweenDate.values().stream()
                .filter(m -> m.getDate().isAfter(startDate) && m.getDate().isBefore(endDate))
                .collect(Collectors.toList());
    }
}