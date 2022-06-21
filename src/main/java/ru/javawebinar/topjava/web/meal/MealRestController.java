package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController {
    private final Logger log = LoggerFactory.getLogger(MealRestController.class);

    @Autowired
    private MealService service;

    public void create(Meal meal) {
        int userId = authUserId();
        log.info("create meal {}, userId {}", meal, userId);
        checkNew(meal);
        service.create(meal, authUserId());
    }

    public void delete(int id) {
        int userId = authUserId();
        log.info("delete meal id {} for user id {}", id, userId);
        service.delete(id, userId);
    }

    public Meal get(int id) {
        int userId = authUserId();
        log.info("get meal id {} for user id {}", id, userId);
        return service.get(id, userId);
    }

    public Collection<MealTo> getAll() {
        int userId = authUserId();
        log.info("getAll for user id {}", userId);
        return service.getAll(userId);
    }

    public void update(Meal meal, int id) {
        int userId = authUserId();
        log.info("update meal {} for user id {}", meal, userId);
        assureIdConsistent(meal, id);
        service.update(meal, userId);
    }

    public List<MealTo> getBetween(@Nullable LocalDate startDate, @Nullable LocalDate endDate, @Nullable LocalTime startTime,
                                   @Nullable LocalTime endTime) {
        int userId = authUserId();
        log.info("getAll filter user id {}", userId);
        return service.getFilter(startDate, endDate, startTime, endTime, userId);
    }
}