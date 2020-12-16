<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Финансы</title>
        <link rel="stylesheet" href="<c:url value="/css/mainStyle.css"/>">
        <link rel="stylesheet" href="<c:url value="/css/menu.css"/>">
        <link rel="stylesheet" href="<c:url value="/css/finance.css"/>">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js"></script>
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

            <input type="radio" name="slideItem" id="slide-item-3" class="slide-toggle" onclick="redirectToEmployees()"/>
            <label for="slide-item-3">
                <p class="icon"><span class="material-icons">supervisor_account</span></p>
                <span class="word">Сотрудники</span>
            </label>

            <input type="radio" name="slideItem" id="slide-item-4" class="slide-toggle" onclick="redirectToFinance()" checked/>
            <label for="slide-item-4">
                <p class="icon"><span class="material-icons">leaderboard</span></p>
                <span class="word">Финансы</span>
            </label>

            <div class="clear"></div>

            <div class="slider">
                <div class="bar"></div>
            </div>
        </nav>

        <div class="popup" onclick="showPopUp()">
            <p class="textPopup">Зачем нужен бюджет?</p>
            <span class="popuptext" id="myPopup">
                Бюджет устанавливается каждый месяц.
                В течение месяца его можно изменять. Оставшаяся прибыль вычисляется
                как "бюджет минус траты на зарплаты и премии за текущий месяц".
            </span>
        </div>

        <p class="text days">До конца месяца
            <span id="left">осталось</span>
            <span id="days" class="countDays">${days}</span>
            <span id="wordDays"></span>.
        </p>

        <p class="title">Отчёт за месяц</p>

        <p class="text">Ваш бюджет в этом месяце составляет <span class="budget">0 ₽</span></p>

        <div>
            <div class="text row">Введите бюджет: </div>
            <input id="inputBudget" class="inputBudget row" maxlength="9">
            <button id="setBudgetButton" class="usualButton updateBudgetButton" type="button" onclick="updateBudget()">
                Изменить бюджет
            </button>
        </div>

        <p class="title subtitle">Расходы на выплаты зарплат и премий</p>
        <p class="text">Зарплаты: <span id="salaries" class="salaryPremium">0 ₽</span></p>
        <p class="text">Премии: <span id="premiums" class="salaryPremium">0 ₽</span></p>
        <p class="text">Всего: <span id="total" class="total">0 ₽</span></p>
        <p class="text">Оставшаяся прибыль: <span id="income" class="balance">0 ₽</span></p>

        <div class="top5">Топ-5 сотрудников по размеру премии</div>
        <canvas id="diagramPremium" class="diagram" width="500" height="400"></canvas>

        <div class="top5">Топ-5 сотрудников по количеству бонусов</div>
        <canvas id="diagramBonus" class="diagram" width="500" height="400"></canvas>

        <div class="top5">Топ-5 сотрудников по количеству штрафов</div>
        <canvas id="diagramFine" class="diagram" width="500" height="400"></canvas>

        <script src="<c:url value="/js/scriptFinance.js"/>"></script>
        <script src="<c:url value="/js/scriptDiagram.js"/>"></script>
        <script src="<c:url value="/js/scriptRedirect.js"/>"></script>
    </body>
</html>
