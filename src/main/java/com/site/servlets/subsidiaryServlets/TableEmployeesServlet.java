package com.site.servlets.subsidiaryServlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.site.entities.Employee;
import com.site.services.EmployeeService;
import com.site.utils.serializers.EmployeeSerializer;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/employees_table")
public class TableEmployeesServlet extends HttpServlet {

    private EmployeeService employeeService;
    private List<String> filters;

    @Override
    public void init() {
        employeeService = new EmployeeService();
        filters = new ArrayList<>();
        filters.add("name");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();

        String value = req.getParameter("request");
        switch (value) {
            case "employees":
                sendEmployees(out);
                break;
            case "filters":
                sendFilters(out);
        }
    }

    private void sendEmployees(PrintWriter out) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Employee.class, new EmployeeSerializer())
                                     .create();
        String empJsonString = gson.toJson(employeeService.getWorkedEmployees());
        out.print(empJsonString);
        out.flush();
    }

    private void sendFilters(PrintWriter out) {
        String filtersJson = new Gson().toJson(filters);
        out.print(filtersJson);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String[] arr = req.getParameterValues("filters[]");
        filters = Arrays.asList(arr);
    }
}
