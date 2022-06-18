package ru.javawebinar.topjava.repository;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.MealsUtil.meals;

public class InMemory implements MealRepository {
    private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger(0);
    private static final Logger log = getLogger(InMemory.class);

    {
        meals.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        log.debug("save meal");
        if (meal.isNew()) {
            meal.setId(idCounter.incrementAndGet());
            repository.put(idCounter.get(), meal);
            return meal;
        }
        return repository.computeIfPresent(meal.getId(), (k, v) -> meal);
    }

    @Override
    public boolean delete(int id) {
        log.debug("delete meal");
        return repository.remove(id) != null;
    }

    @Override
    public Meal get(int id) {
        log.debug("get meal");
        return repository.get(id);
    }

    public Collection<Meal> getAll() {
        log.debug("get all");
        return repository.values();
    }
}
