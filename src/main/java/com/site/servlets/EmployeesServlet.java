package com.site.servlets;

import com.site.entities.Employee;
import com.site.entities.Position;
import com.site.services.EmployeeService;
import com.site.services.PositionService;
import com.site.utils.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/employees")
public class EmployeesServlet extends HttpServlet {

    private static final String PATH = "/jsp/employees.jsp";
    private EmployeeService employeeService;
    private PositionService positionService;

    @Override
    public void init() {
        positionService = new PositionService();
        employeeService = new EmployeeService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if(id != null) {
            String path = req.getContextPath() + "/profile?id=" + id;
            resp.sendRedirect(path);
        } else {
            req.setAttribute("countFired", employeeService.getFiredEmployees().size());
            req.setAttribute("positions", positionService.getAllPositions());
            req.getRequestDispatcher(PATH).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String surname = req.getParameter("surname");
        String name = req.getParameter("name");
        String patronymic = req.getParameter("patronymic");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String sex = req.getParameter("sex");
        String dateStr = req.getParameter("hired");
        String positionName = req.getParameter("position");
        int salary = Integer.parseInt(req.getParameter("salary"));

        Date date = DateUtil.parseDate(dateStr);

        Employee employee = new Employee(name, surname, patronymic,
                                        email, phone, sex, salary,
                                        null, null, date,null, null);
        Position position = new Position(positionName);

        if (!positionService.contains(positionName)) {
            position.addEmployee(employee);
            employee.setPosition(position);
            positionService.savePosition(position);
        } else {
            employee.setPosition(positionService.getPosition(positionName));
            employeeService.saveEmployee(employee);
        }

        doGet(req, resp);
    }
}
