<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.site.entities.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Уволенные</title>
        <link rel="stylesheet" href="<c:url value="/css/mainStyle.css"/>">
        <link rel="stylesheet" href="<c:url value="/css/menu.css"/>">
        <link rel="stylesheet" href="<c:url value="/css/table.css"/>">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>
    <body>
        <nav class="slideMenu">
            <input type="radio" name="slideItem" id="slide-item-1" class="slide-toggle" onclick="redirectToDashboard()"/>
            <label for="slide-item-1">
                <p class="icon"><span class="material-icons">dashboard</span></p>
                <span class="word">Рабочий стол</span>
            </label>

            <input type="radio" name="slideItem" id="slide-item-2" class="slide-toggle" onclick="redirectToTasks()"/>
            <label for="slide-item-2">
                <p class="icon"><span class="material-icons">receipt_long</span></p>
                <span class="word">Задачи</span>
            </label>

            <input type="radio" name="slideItem" id="slide-item-3" class="slide-toggle" onclick="redirectToEmployees()" checked/>
            <label for="slide-item-3">
                <p class="icon"><span class="material-icons">supervisor_account</span></p>
                <span class="word">Сотрудники</span>
            </label>

            <input type="radio" name="slideItem" id="slide-item-4" class="slide-toggle" onclick="redirectToFinance()"/>
            <label for="slide-item-4">
                <p class="icon"><span class="material-icons">leaderboard</span></p>
                <span class="word">Финансы</span>
            </label>

            <div class="clear"></div>

            <div class="slider">
                <div class="bar"></div>
            </div>
        </nav>

        <div class="usualMenu">
            <a href="/employees">Все сотрудники ${workEmployees.size()}</a>
            <a id="menuItem" href="/employees/fired">Уволенные ${firedEmployees.size()}</a>
        </div>

        <table class="tableFired">
            <thead>
                <tr>
                    <th>Сотрудник</th>
                    <th>Бывшая должность</th>
                    <th>Дата увольнения</th>
                    <th>Почта</th>
                    <th>Телефон</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Employee> employees = (List<Employee>) request.getAttribute("firedEmployees");
                    SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
                    if (employees != null && !employees.isEmpty()) {
                        for (Employee employee : employees) {
                            out.println("<tr>");
                            out.println("<td> <a class=\"nameEmployee\" href=\"/profile?id="
                                                + employee.getId() + "&action=fired\">"
                                                + employee.getSurname() + " "
                                                + employee.getName() + " "
                                                + employee.getPatronymic()
                                                + "</a></td>");
                            out.println("<td>" + employee.getPosition().getName() + "</td>");
                            out.println("<td>" + formatter.format(employee.getFired()) + "</td>");
                            out.println("<td>" + employee.getEmail() + "</td>");
                            out.println("<td>" + employee.getPhone() + "</td>");
                            out.println("</tr>");
                        }
                    }
                %>
            </tbody>
        </table>

        <script src="<c:url value="/js/scriptRedirect.js"/>"></script>
        <script>
            $("#menuItem").css("text-decoration", "underline");
        </script>
    </body>
</html>
