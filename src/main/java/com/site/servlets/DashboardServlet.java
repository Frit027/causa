package com.site.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.site.entities.Employee;
import com.site.services.EmployeeService;
import com.site.services.TaskService;
import com.site.utils.serializers.BirthdaySerializer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    private static final String PATH = "/jsp/dashboard.jsp";
    private TaskService taskService;
    private EmployeeService employeeService;
    private PrintWriter out;

    @Override
    public void init() {
        taskService = new TaskService();
        employeeService = new EmployeeService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String value = req.getParameter("request");
        if (value == null) {
            req.setAttribute("tasks", taskService.gelAllTasks());
            req.getRequestDispatcher(PATH).forward(req, resp);
        } else {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out = resp.getWriter();

            switch (value) {
                case "birthdays":
                    sendBirthdays();
                    break;
                case "countTasks":
                    sendCountTasks();
            }
        }
    }

    private void sendBirthdays() {
        Gson gson = new GsonBuilder().registerTypeAdapter(Employee.class, new BirthdaySerializer())
                                     .create();
        String birthdaysJsonString = gson.toJson(employeeService.getAll());

        out.print(birthdaysJsonString);
        out.flush();
    }

    private void sendCountTasks() {
        String count = new Gson().toJson(taskService.gelAllTasks().size());
        out.print(count);
        out.flush();
    }
}
