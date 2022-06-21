package ru.javawebinar.topjava.service;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

import static ru.javawebinar.topjava.util.DateTimeUtil.getEndDateOrMax;
import static ru.javawebinar.topjava.util.DateTimeUtil.getStartDateOrMin;
import static ru.javawebinar.topjava.util.MealsUtil.*;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public void create(Meal meal, int userId) {
        repository.save(meal, userId);
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    public Meal get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    public Collection<MealTo> getAll(int userId) {
        return getTos(repository.getAll(userId), DEFAULT_CALORIES_PER_DAY);
    }

    public void update(Meal meal, int userId) {
        checkNotFoundWithId(repository.save(meal, userId), meal.getId());
    }

    public List<MealTo> getFilter(@Nullable LocalDate startDate, @Nullable LocalDate endDate, @Nullable LocalTime startTime,
                                  @Nullable LocalTime endTime, int userId) {
        List<Meal> mealsBetweenDate = repository.getAllBetweenDate(getStartDateOrMin(startDate), getEndDateOrMax(endDate), userId);
        return getFilteredTos(mealsBetweenDate, DEFAULT_CALORIES_PER_DAY, startTime, endTime);
    }
}