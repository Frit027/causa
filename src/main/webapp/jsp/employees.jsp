<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="positions" scope="request" type="java.util.List"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Сотрудники</title>
        <link rel="stylesheet" href="<c:url value="/css/mainStyle.css"/>">
        <link rel="stylesheet" href="<c:url value="/css/table.css"/>">
        <link rel="stylesheet" href="<c:url value="/css/menu.css"/>">
        <link rel="stylesheet" href="<c:url value="/css/employees.css"/>">
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

        <div class="leftMenu">
            <div class="usualMenu">
                <a id="menuItem" href="/employees">Все сотрудники <span id="countWorking"></span></a>
                <a href="/employees/fired">Уволенные ${countFired}</a>
            </div>

            <button id="button" class="mainButton addEmployee">Добавить сотрудника</button>
        </div>

        <dialog>
            <div class="title_dialog">Добавление сотрудника</div>

            <div class="group">
                <input id="surname" class="input_dialog" type="text" required>
                <span class="highlight"></span>
                <span class="bar"></span>
                <label class="label_dialog">Фамилия</label>
            </div>

            <div class="group">
                <input id="name" class="input_dialog" type="text" required>
                <span class="highlight"></span>
                <span class="bar"></span>
                <label class="label_dialog">Имя</label>
            </div>

            <div class="group">
                <input id="patronymic" class="input_dialog" type="text" required>
                <span class="highlight"></span>
                <span class="bar"></span>
                <label class="label_dialog">Отчество</label>
            </div>

            <div class="group">
                <input id="email" class="input_dialog" type="email" required>
                <span class="highlight"></span>
                <span class="bar"></span>
                <label class="label_dialog">Почта</label>
            </div>

            <div class="group">
                <input id="phone" class="input_dialog" type="tel" required>
                <span class="highlight"></span>
                <span class="bar"></span>
                <label class="label_dialog">Телефон</label>
            </div>

            <div class="group">
                <input id="salary" class="input_dialog" type="number" required>
                <span class="highlight"></span>
                <span class="bar"></span>
                <label class="label_dialog">Зарплата</label>
            </div>

            <div class="nameInDialog">Пол</div>
            <div>
                <label class="radio sex">
                    <input name="sex" type="radio" id="male">
                    <span>Мужчина</span>
                </label>
                <label class="radio sex">
                    <input name="sex" type="radio" id="female">
                    <span>Женщина</span>
                </label>
            </div>

            <label for="hired" class="nameInDialog">Принят</label>
            <input id="hired" class="inputHired" type="date">

            <div>
                <label class="nameInDialog">Должность</label>
                <input list="positions" id="position" class="inputPosition" type="text" required>
                <datalist id="positions">
                    <c:forEach var="position" items="${positions}">
                        <option value="${position.name}">
                    </c:forEach>
                </datalist>
            </div>

            <menu class="buttons">
                <button id="cancel" type="button" class="usualButton">Отменить</button>
                <button id="confirm" type="button" class="usualButton">Добавить</button>
            </menu>
        </dialog>

        <table class="tableEmployees">
            <thead>
                <tr>
                    <th class="add_column" rowspan="0">
                        <span id="icon" onclick="showCheckboxes()" class="material-icons">
                            playlist_add
                        </span>
                        <div id="checkboxes">
                            <label for="nameCheckbox"><input id="nameCheckbox" type="checkbox" disabled checked>Имя</label>
                            <label for="positionCheckbox"><input id="positionCheckbox" type="checkbox">Должность</label>
                            <label for="emailCheckbox"><input id="emailCheckbox" type="checkbox">Email</label>
                            <label for="phoneCheckbox"><input id="phoneCheckbox" type="checkbox">Телефон</label>
                            <label for="sexCheckbox"><input id="sexCheckbox" type="checkbox">Пол</label>
                            <label for="hiredCheckbox"><input id="hiredCheckbox" type="checkbox">День приёма на работу</label>
                            <label for="salaryCheckbox"><input id="salaryCheckbox" type="checkbox">Зарплата</label>
                            <label for="birthdayCheckbox"><input id="birthdayCheckbox" type="checkbox">День рождения</label>
                        </div>
                    </th>
                </tr>
            </thead>
            <tbody></tbody>
        </table>

        <script src="<c:url value="/js/scriptEmployees.js"/>"></script>
        <script src="<c:url value="/js/scriptRedirect.js"/>"></script>
    </body>
</html>