<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="employees" scope="request" type="java.util.List"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Задачи</title>
        <link rel="stylesheet" href="<c:url value="/css/nice-select.css"/>">
        <link rel="stylesheet" href="<c:url value="/css/mainStyle.css"/>">
        <link rel="stylesheet" href="<c:url value="/css/tasks.css"/>">
        <link rel="stylesheet" href="<c:url value="/css/menu.css"/>">
        <link rel="stylesheet" href="<c:url value="/css/table.css"/>">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="<c:url value="/js/nice-select.min.js"/>"></script>
    </head>
    <body>
        <nav class="slideMenu">
            <input type="radio" name="slideItem" id="slide-item-1" class="slide-toggle" onclick="redirectToDashboard()"/>
            <label for="slide-item-1">
                <p class="icon"><span class="material-icons">dashboard</span></p>
                <span class="word">Рабочий стол</span>
            </label>

            <input type="radio" name="slideItem" id="slide-item-2" class="slide-toggle" onclick="redirectToTasks()" checked/>
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

        <button id="buttonShowDialog" class="mainButton">Поставить задачу</button>

        <dialog>
            <div class="title_dialog">Создание задачи</div>

            <div class="group">
                <input id="name" class="input_dialog" type="text" required>
                <span class="highlight"></span>
                <span class="bar"></span>
                <label class="label_dialog">Название задачи</label>
            </div>

            <div class="group">
                <input id="description" class="input_dialog" type="text" required>
                <span class="highlight"></span>
                <span class="bar"></span>
                <label class="label_dialog">Краткое описание</label>
            </div>

            <label for="deadline" class="nameInDialog">Дедлайн</label>
            <input id="deadline" type="date">

            <div class="selects_dialog">
                <div class="name_dialog">Ответственный</div>
                <select class="select" id="executor">
                    <c:forEach var="employee" items="${employees}">
                        <option value="${employee.id}">
                            <c:out value="${employee.surname} ${employee.name} ${employee.patronymic}" />
                        </option>
                    </c:forEach>
                </select>

                <div class="name_dialog">Приоритет</div>
                <select class="select" id="priority">
                    <option value="1">Низкий</option>
                    <option value="2">Средний</option>
                    <option value="3">Высокий</option>
                </select>
            </div>

            <menu>
                <button id="cancel" class="usualButton">Отменить</button>
                <button id="confirm" class="usualButton">Поставить задачу</button>
            </menu>
        </dialog>

        <form method="post" action="<c:url value="/tasks_table"/>">
            <div id="taskModal" class="modal">
                <div class="modal-content">
                    <div class="modal-header">
                        <h2>Завершение задачи</h2>
                    </div>

                    <div class="modal-body">
                        <p id="pExecutor"></p>
                        <p>Назначить премию (% от зарплаты):</p>
                        <div>
                            <label class="radio">
                                <input name="bonus" type="radio" id="bonus_5">
                                <span>5 %</span>
                            </label>
                            <label class="radio">
                                <input name="bonus" type="radio" id="bonus_10">
                                <span>10 %</span>
                            </label>
                            <label class="radio">
                                <input name="bonus" type="radio" id="bonus_15">
                                <span>15 %</span>
                            </label>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <menu class="buttonsCompleteTask">
                            <button id="close" class="usualButton" type="reset">Закрыть</button>
                            <button id="setBonusButton" class="usualButton" name="bonusButton" type="submit">
                                Завершить задачу
                            </button>
                        </menu>
                    </div>
                </div>
            </div>
        </form>

        <table>
            <thead>
                <tr>
                    <th>Задача</th>
                    <th>Дедлайн</th>
                    <th>Ответственный</th>
                    <th>Описание</th>
                    <th id="priorityTitle" onclick="getSortTasks()">Приоритет ⇕</th>
                    <th></th>
                </tr>
            </thead>
            <tbody></tbody>
        </table>

        <script src="<c:url value="/js/scriptTasks.js"/>"></script>
        <script src="<c:url value="/js/scriptRedirect.js"/>"></script>
    </body>
</html>