package com.site.servlets;

import com.site.entities.Employee;
import com.site.entities.Task;
import com.site.services.EmployeeService;
import com.site.services.TaskService;
import com.site.utils.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/tasks")
public class TasksServlet extends HttpServlet {

    private static final String PATH = "/jsp/tasks.jsp";
    private EmployeeService employeeService;
    private TaskService taskService;

    @Override
    public void init() {
        employeeService = new EmployeeService();
        taskService = new TaskService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("employees", employeeService.getWorkedEmployees());
        req.getRequestDispatcher(PATH).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        int employeeId = Integer.parseInt(req.getParameter("executor"));
        String description = req.getParameter("description");
        String deadline = req.getParameter("deadline");
        int priority = Integer.parseInt(req.getParameter("priority"));

        Date date = DateUtil.parseDate(deadline);

        Employee employee = employeeService.getEmployee(employeeId);
        Task task = new Task(name, description, date, priority);

        task.setEmployee(employee);
        employee.addTask(task);
        taskService.saveTask(task);
    }
}
