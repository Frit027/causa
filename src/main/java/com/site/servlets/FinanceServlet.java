package com.site.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.site.entities.Employee;
import com.site.entities.Finance;
import com.site.services.EmployeeService;
import com.site.services.FinanceService;
import com.site.utils.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/finance")
public class FinanceServlet extends HttpServlet {

    private static final String PATH = "/jsp/finance.jsp";
    private FinanceService financeService;
    private EmployeeService employeeService;
    private PrintWriter out;

    @Override
    public void init() {
        financeService = new FinanceService();
        employeeService = new EmployeeService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String value = req.getParameter("request");
        if (value == null) {
            req.setAttribute("days", DateUtil.getDaysLeftInMonth());
            req.getRequestDispatcher(PATH).forward(req, resp);
        } else {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out = resp.getWriter();

            switch (value) {
                case "finance":
                    sendFinance();
                    break;
                case "salary":
                    sendSalary();
                    break;
                case "diagram":
                    sendDataForDiagram();
            }
        }
    }

    private void sendFinance() {
        Gson gson = new GsonBuilder().setDateFormat("d MMMM yyyy")
                                     .create();
        List<Finance> finances = financeService.getAll();
        Finance lastFinance = finances.isEmpty() ? null : finances.get(finances.size() - 1);

        String data;
        if (lastFinance == null || !DateUtil.isSameMonth(lastFinance.getMonth())) {
            data = gson.toJson(new ArrayList<>());
        } else {
            data = gson.toJson(lastFinance);
        }

        out.print(data);
        out.flush();
    }

    private void sendSalary() {
        List<Double> list = new ArrayList<>();
        list.add(employeeService.getTotalSalary());
        list.add(employeeService.getTotalPremium());

        String data = new Gson().toJson(list);
        out.print(data);
        out.flush();
    }

    private void sendDataForDiagram() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
                                     .create();
        List<Employee> list = employeeService.getWorkedEmployees();
        List<Employee> top5Premium = employeeService.getTop5Premium(list);
        List<Employee> top5Bonus = employeeService.getTop5Bonus(list);
        List<Employee> top5Fine = employeeService.getTop5Fine(list);

        String json = "{\"premium\":" + gson.toJson(top5Premium)
                        + ", \"bonus\":" + gson.toJson(top5Bonus)
                        + ", \"fine\":" + gson.toJson(top5Fine) + "}";

        String data = gson.toJson(json);
        out.print(data);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        double budget = Double.parseDouble(req.getParameter("budget"));
        double income = budget - (employeeService.getTotalSalary() + employeeService.getTotalPremium());
        String id = req.getParameter("id");

        Finance finance = new Finance(budget, income, new Date());
        if (id != null && DateUtil.isSameMonth(finance.getMonth())) {
            finance.setId(Integer.parseInt(id));
            financeService.updateFinance(finance);
        } else {
            financeService.saveFinance(finance);
        }

        doGet(req, resp);
    }
}
