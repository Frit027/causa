<%@ page import="java.util.List" %>
<%@ page import="com.site.entities.Task" %>
<%@ page import="com.site.utils.DateUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Рабочий стол</title>
        <link rel="stylesheet" href="<c:url value="/css/mainStyle.css"/>">
        <link rel="stylesheet" href="<c:url value="/css/menu.css"/>">
        <link rel="stylesheet" href="<c:url value="/css/dashboard.css"/>">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://code.jquery.com/ui/1.11.3/jquery-ui.js"></script>
    </head>
    <body>
        <nav class="slideMenu">
            <input type="radio" name="slideItem" id="slide-item-1" class="slide-toggle" onclick="redirectToDashboard()" checked/>
            <label for="slide-item-1">
                <p class="icon"><span class="material-icons">dashboard</span></p>
                <span class="word">Рабочий стол</span>
            </label>

            <input type="radio" name="slideItem" id="slide-item-2" class="slide-toggle" onclick="redirectToTasks()"/>
            <label for="slide-item-2">
                <p class="icon"><span class="material-icons">receipt_long</span></p>
                <span class="word">Задачи</span>
            </label>

            <input type="radio" name="slideItem" id="slide-item-3" class="slide-toggle" onclick="redirectToEmployees()"/>
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

        <div id="card_tasks" class="card">
            <div class="container">
                <div class="title">Задачи <span class="count"></span></div>
                <%
                    for (Task task : (List<Task>) request.getAttribute("tasks")) {
                        String html = DateUtil.isDateNotExpired(task.getDeadline())
                                                ? "<div class=\"taskInProgress\">" + task.getName() + "</div>"
                                                : "<div class=\"taskExpired\">" + task.getName() + "</div>";
                        out.println(html);
                    }
                %>
            </div>
        </div>

        <div id="card_birthdays" class="card">
            <div id="birthdays" class="container">
                <div class="title">Дни рождения</div>
            </div>
        </div>

        <script src="<c:url value="/js/scriptDashboard.js"/>"></script>
        <script src="<c:url value="/js/scriptRedirect.js"/>"></script>
    </body>
</html>