package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.InMemory;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.MealsUtil.getMealsTo;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    private final MealRepository repository = new InMemory();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                log.info("delete meals");
                repository.delete(Integer.parseInt(req.getParameter("id")));
                resp.sendRedirect("meals");
                break;
            case "all":
                log.info("get all meals");
                req.setAttribute("mealsTo", getMealsTo(repository.getAll()));
                req.getRequestDispatcher("/meals.jsp").forward(req, resp);
                break;
            case "edit":
                log.info("edit meal");
                req.setAttribute("meal", repository.get(Integer.parseInt(req.getParameter("id"))));
                req.getRequestDispatcher("/mealsform.jsp").forward(req, resp);
                break;
            case "create":
                log.info("create meal");
                req.setAttribute("meal", new Meal(null, LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES),
                        "", 1000));
                req.getRequestDispatcher("/mealsform.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("save/edit meal");
        req.setCharacterEncoding("UTF-8");

        String id = req.getParameter("id");
        LocalDateTime localDateTime = LocalDateTime.parse(req.getParameter("dateTime"));
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));

        Meal meal = new Meal(id.isEmpty() ? null : Integer.parseInt(id), localDateTime, description, calories);
        repository.save(meal);
        resp.sendRedirect("meals");
    }
}
