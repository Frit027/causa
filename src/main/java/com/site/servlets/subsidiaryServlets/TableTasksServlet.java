package com.site.servlets.subsidiaryServlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.site.entities.Employee;
import com.site.entities.Task;
import com.site.services.EmployeeService;
import com.site.services.TaskService;
import com.site.utils.serializers.TaskSerializer;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;

@WebServlet("/tasks_table")
public class TableTasksServlet extends HttpServlet {

    private int countClick;
    private static final String SORT = "SORT";
    private TaskService taskService;
    private EmployeeService employeeService;

    @Override
    public void init() {
        countClick = -1;
        taskService = new TaskService();
        employeeService = new EmployeeService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting()
                                     .registerTypeAdapter(Task.class, new TaskSerializer())
                                     .create();
        List<Task> tasks = taskService.gelAllTasks();

        String action = req.getParameter("action");
        if (action != null && action.equals(SORT)) {
            countClick++;
        }
        sortByPriority(tasks);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();
        String taskJsonString = gson.toJson(tasks);
        taskJsonString = taskJsonString.substring(0, taskJsonString.length() - 1)
                         + ", {\"countClick\": "
                         + countClick + "}]";
        out.print(taskJsonString);
        out.flush();
    }

    private void sortByPriority(List<Task> tasks) {
        if (countClick % 3 == 0) {
            tasks.sort(Comparator.comparing(Task::getPriority));
        } else if (countClick % 3 == 1){
            tasks.sort(Comparator.comparing(Task::getPriority).reversed());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("bonusButton"));
        String bonus = req.getParameter("bonus");

        Task task = taskService.getTask(id);
        Employee emp = task.getEmployee();

        if (bonus != null) {
            employeeService.addPremium(emp, Integer.parseInt(bonus));
        }

        if (taskService.isTaskOverdue(task)) {
            employeeService.addFine(emp);
        } else {
            employeeService.addBonus(emp);
        }

        emp.removeTask(task);
        taskService.deleteTask(task);

        String path = req.getContextPath() + "/tasks";
        resp.sendRedirect(path);
    }
}
