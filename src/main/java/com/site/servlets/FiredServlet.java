package com.site.servlets;

import com.site.services.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/employees/fired")
public class FiredServlet extends HttpServlet {

    private static final String PATH = "/jsp/fired.jsp";
    private EmployeeService employeeService;

    @Override
    public void init() {
        employeeService = new EmployeeService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("firedEmployees", employeeService.getFiredEmployees());
        req.setAttribute("workEmployees", employeeService.getWorkedEmployees());
        req.getRequestDispatcher(PATH).forward(req, resp);
    }
}
