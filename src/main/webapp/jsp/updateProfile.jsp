<jsp:useBean id="employee" scope="request" type="com.site.entities.Employee"/>
<jsp:useBean id="messengers" scope="request" type="java.util.List"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Редактирование сотрудника</title>
        <link rel="stylesheet" href="<c:url value="/css/nice-select.css"/>">
        <link rel="stylesheet" href="<c:url value="/css/mainStyle.css"/>">
        <link rel="stylesheet" href="<c:url value="/css/menu.css"/>">
        <link rel="stylesheet" href="<c:url value="/css/profile.css"/>">
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

        <div class="containerUpdate">
            <div class="title">Редактирование сотрудника</div>

            <button id="save_button" class="usualButton update" onclick="save(${employee.id})">Сохранить</button>

            <div class="containerColumn">
                <div class="profile_card update">
                    <div class="subtitle">Основная информация</div>

                    <div class="photo">
                        <img class="img" src="<c:url value="/images/man_min.png"/>" alt="man">
                    </div>

                    <div class="container">
                        <div class="line">
                            <div class="text">Фамилия</div>
                            <div class="col-3">
                                <input id="surname" class="effect-1" type="text" value="${employee.surname}" placeholder="Введите фамилию">
                                <span class="focus-border"></span>
                            </div>
                        </div>

                        <div class="line">
                            <div class="text">Имя</div>
                            <div class="col-3">
                                <input id="name" class="effect-1" type="text" value="${employee.name}" placeholder="Введите имя">
                                <span class="focus-border"></span>
                            </div>
                        </div>

                        <div class="line">
                            <div class="text">Отчество</div>
                            <div class="col-3">
                                <input id="patronymic" class="effect-1" type="text" value="${employee.patronymic}" placeholder="Введите отчество">
                                <span class="focus-border"></span>
                            </div>
                        </div>

                        <div class="line">
                            <div class="text">Пол</div>
                            <div>
                                <c:if test="${employee.sex == 'male'}">
                                    <label class="radio update">
                                        <input name="sex" type="radio" value="male" checked>
                                        <span>Мужчина</span>
                                    </label>
                                    <label class="radio update">
                                        <input name="sex" type="radio" value="female">
                                        <span>Женщина</span>
                                    </label>
                                </c:if>
                                <c:if test="${employee.sex == 'female'}">
                                    <label class="radio update">
                                        <input name="sex" type="radio" value="male">
                                        <span>Мужчина</span>
                                    </label>
                                    <label class="radio update">
                                        <input name="sex" type="radio" value="female" checked>
                                        <span>Женщина</span>
                                    </label>
                                </c:if>
                            </div>
                        </div>

                        <div class="line">
                            <div class="text">Должность</div>
                            <div class="col-3">
                                <input id="position" class="effect-1" type="text" value="${employee.position.name}" placeholder="Введите должность">
                                <span class="focus-border"></span>
                            </div>
                        </div>

                        <div class="line">
                            <div class="text">Зарплата</div>
                            <div class="col-3">
                                <input id="salary" class="effect-1" type="number" value="${employee.salary}" placeholder="Введите зарплату">
                                <span class="focus-border"></span>
                            </div>
                        </div>

                        <div class="line">
                            <div class="text">День рождения</div>
                            <input id="birthday" type="date" value="${employee.birthday}">
                        </div>

                        <div class="line">
                            <div class="text">День приёма на работу</div>
                            <input id="hired" type="date"  value="${employee.hired}">
                        </div>

                        <div class="line">
                            <div class="text">Обо мне</div>
                            <div class="col-3">
                                <input id="hobby" class="effect-1" type="text" value="${employee.hobby}" placeholder="Введите ваши хобби">
                                <span class="focus-border"></span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="profile_card">
                    <div class="subtitle">Контакты</div>

                    <div class="line">
                        <div class="text">Телефон</div>
                        <div class="col-3">
                            <input id="phone" class="effect-1" type="tel" value="${employee.phone}" placeholder="Введите телефон">
                            <span class="focus-border"></span>
                        </div>
                    </div>

                    <div class="line">
                        <div class="text">Email</div>
                        <div class="col-3">
                            <input id="email" class="effect-1" type="text" value="${employee.email}" placeholder="Введите email">
                            <span class="focus-border"></span>
                        </div>
                    </div>

                    <div class="line">
                        <div class="text">Мессенджер</div>
                        <div class="col-3">
                            <input id="messenger" class="effect-1" type="text" value="${employee.messengerName}" placeholder="Введите никнейм">
                            <span class="focus-border"></span>
                        </div>
                    </div>

                    <label for="messengerType"></label>
                    <select id="messengerType" class="messengers">
                        <c:forEach var="messenger" items="${messengers}">
                            <option value="${messenger.id}">
                                <c:out value="${messenger.name}" />
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>

        <script src="<c:url value="/js/scriptUpdateProfile.js"/>"></script>
        <script src="<c:url value="/js/scriptRedirect.js"/>"></script>
    </body>
</html>
