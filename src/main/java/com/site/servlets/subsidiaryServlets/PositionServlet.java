package com.site.servlets.subsidiaryServlets;

import com.google.gson.Gson;
import com.site.entities.Coordinates;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/position")
public class PositionServlet extends HttpServlet {

    private int topTask;
    private int leftTask;

    private int topBirthday;
    private int leftBirthday;

    @Override
    public void init() {
        topTask = 120;
        leftTask = 5;

        topBirthday = 120;
        leftBirthday = 420;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        Coordinates posTask = new Coordinates(topTask, leftTask);
        Coordinates posBirthday = new Coordinates(topBirthday, leftBirthday);

        List<Coordinates> list = new ArrayList<>();
        list.add(posTask);
        list.add(posBirthday);

        String json = new Gson().toJson(list);
        out.print(json);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        switch (id) {
            case "task":
                topTask = (int) Double.parseDouble(req.getParameter("top"));
                leftTask = (int) Double.parseDouble(req.getParameter("left"));
                break;
            case "birthday":
                topBirthday = (int) Double.parseDouble(req.getParameter("top"));
                leftBirthday = (int) Double.parseDouble(req.getParameter("left"));
        }
    }
}
