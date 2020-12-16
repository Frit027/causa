package com.site.servlets;

import com.site.entities.Employee;
import com.site.entities.Task;
import com.site.services.EmployeeService;
import com.site.services.TaskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private static final String PATH = "/jsp/profile.jsp";
    private EmployeeService employeeService;
    private TaskService taskService;
    private int id;

    @Override
    public void init() {
        employeeService = new EmployeeService();
        taskService = new TaskService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        id = Integer.parseInt(req.getParameter("id"));
        setAttributesForPage(req);

        Action action = null;
        String actionStr = req.getParameter("action");
        if (actionStr != null) {
            action = Action.valueOf(actionStr.toUpperCase());
        }
        actionAnalysis(req, resp, action);

        if (action != Action.REMOVE) {
            req.getRequestDispatcher(PATH).forward(req, resp);
        }
    }

    private void setAttributesForPage(HttpServletRequest req) {
        Employee emp = employeeService.getEmployee(id);
        List<Task> tasks = emp.getTasks();

        req.setAttribute("employee", emp);
        req.setAttribute("tasks", tasks);
        req.setAttribute("isEmpty", tasks.isEmpty());
    }

    private void actionAnalysis(HttpServletRequest req, HttpServletResponse resp, Action action) throws IOException {
        if (action != null) {
            switch (action) {
                case FIRED:
                    req.setAttribute("isFired", true); //функционал
                    checkTasks(req);
                    break;
                case RETURN:
                    req.setAttribute("isReturn", true);
                    req.setAttribute("isFired", false);
                    employeeService.returnToWork(id);
                    break;
                case REMOVE:
                    employeeService.deleteEmployee(id);
                    String path = req.getContextPath() + "/employees";
                    resp.sendRedirect(path);
            }
        } else {
            req.setAttribute("isFired", false);
        }
    }

    private void checkTasks(HttpServletRequest req) {
        if (employeeService.getEmployee(id).getTasks().size() != 0) {
            req.setAttribute("showDialog", true);
            req.setAttribute("employees", employeeService.getWorkedEmployeesWithoutEmp(id));
        } else {
            employeeService.setFired(id);
            req.setAttribute("fired", true); //визуализация
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Action action = Action.valueOf(req.getParameter("postAction").toUpperCase());

        switch (action) {
            case SET:
                setNewExecutor(req);
                break;
            case REMOVE:
                removeTasks();
        }
    }

    private void setNewExecutor(HttpServletRequest req) {
        req.setAttribute("showDialog", false);
        req.setAttribute("fired", true); //визуализация

        employeeService.setFired(id);

        int newExecutorId = Integer.parseInt(req.getParameter("newExecutorId"));
        Employee newExecutor = employeeService.getEmployee(newExecutorId);
        List<Task> tasks = employeeService.getAllTasksById(id);

        taskService.setExecutor(tasks, newExecutor);

        newExecutor.setTasks(tasks);
        employeeService.updateEmployee(newExecutor);
    }

    private void removeTasks() {
        taskService.deleteTasks(employeeService.getAllTasksById(id));

        Employee emp = employeeService.getEmployee(id);
        emp.setTasks(new ArrayList<>());
        employeeService.updateEmployee(emp);
    }

    enum Action {
        SET,
        FIRED,
        REMOVE,
        RETURN
    }
}
