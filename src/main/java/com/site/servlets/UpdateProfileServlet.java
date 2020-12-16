package com.site.servlets;

import com.google.gson.Gson;
import com.site.entities.Employee;
import com.site.entities.Position;
import com.site.services.EmployeeService;
import com.site.services.MessengerService;
import com.site.services.PositionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile/edit")
public class UpdateProfileServlet extends HttpServlet {

    private static final String PATH = "/jsp/updateProfile.jsp";
    private EmployeeService employeeService;
    private PositionService positionService;
    private MessengerService messengerService;

    @Override
    public void init() {
        employeeService = new EmployeeService();
        positionService = new PositionService();
        messengerService = new MessengerService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        req.setAttribute("employee", employeeService.getEmployee(Integer.parseInt(id)));
        req.setAttribute("messengers", messengerService.getAllMessengers());
        req.getRequestDispatcher(PATH).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String empJsonString = req.getParameter("employee");
        String position = req.getParameter("position");
        String messengerId = req.getParameter("messengerId");

        Employee newEmployee = new Gson().fromJson(empJsonString, Employee.class);
        newEmployee.setId(Integer.parseInt(id));
        Position newPosition = new Position(position);

        if (!positionService.contains(position)) {
            newPosition.addEmployee(newEmployee);
            newEmployee.setPosition(newPosition);
            positionService.savePosition(newPosition);
        } else {
            newEmployee.setPosition(positionService.getPosition(position));
        }

        if (newEmployee.getMessengerName() != null) {
            newEmployee.setMessenger(messengerService.getMessenger(Integer.parseInt(messengerId)));
        }

        employeeService.updateEmployee(newEmployee);

        doGet(req, resp);
    }
}
