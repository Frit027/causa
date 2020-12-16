<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.site.entities.Employee" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="employee" scope="request" class="com.site.entities.Employee"/>
<jsp:useBean id="tasks" scope="request" type="java.util.List"/>
<!DOCTYPE html>
<html>
    <head>
        <title>${employee.surname} ${employee.name}</title>
        <link rel="stylesheet" href="<c:url value="/css/nice-select.css"/>">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="<c:url value="/css/mainStyle.css"/>">
        <link rel="stylesheet" href="<c:url value="/css/menu.css"/>">
        <link rel="stylesheet" href="<c:url value="/css/profile.css"/>">
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

        <c:if test="${showDialog == true}">
            <dialog>
                <div class="text">
                    <div class="sentence">
                        У сотрудника <span class="name">${employee.surname} ${employee.name}</span> остались невыполненные задачи:
                    </div>
                    <ul>
                        <c:forEach var="task" items="${tasks}">
                            <li><span>${task.name}</span></li>
                        </c:forEach>
                    </ul>

                    <div>
                        Назначьте <span class="new">нового</span> ответсвенного или <span class="remove">удалите</span> задачи.
                    </div>
                    <br>

                    <div class="row">
                        <label for="executor" class="executor">Ответственный:</label>
                        <select id="executor" class="list">
                            <jsp:useBean id="employees" scope="request" type="java.util.List"/>
                            <c:forEach var="employee" items="${employees}">
                                <option value="${employee.id}">
                                    <c:out value="${employee.surname} ${employee.name} ${employee.patronymic}" />
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <menu class="buttons">
                    <button id="delete_tasks_button" class="usualButton" onclick="removeTasks(${employee.id})">
                        Удалить задачи
                    </button>
                    <button id="save_button" class="usualButton" onclick="setNewExecutor(${employee.id})">
                        Сохранить
                    </button>
                </menu>
            </dialog>
        </c:if>

        <div class="title_profile">
            <div class="title">${employee.surname} ${employee.name} ${employee.patronymic}</div>
        </div>

        <c:if test="${isFired == false}">
        <div class="title_profile">
            <a href="${pageContext.request.contextPath}/profile/edit?id=${employee.id}">
                <span class="material-icons action">edit</span>
            </a>
        </div>
        </c:if>

        <div class="title_profile">
            <div class="dropdown">
                <span class="material-icons action" data-toggle="dropdown">menu</span>
                <c:if test="${isFired == true}">
                    <ul class="dropdown-menu">
                        <li>
                            <a href="${pageContext.request.contextPath}/profile?id=${employee.id}&action=return">
                                Взять обратно
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/profile?id=${employee.id}&action=remove">
                                Удалить все данные
                            </a>
                        </li>
                    </ul>
                </c:if>
                <c:if test="${isFired == false}">
                    <ul class="dropdown-menu">
                        <li>
                            <a href="${pageContext.request.contextPath}/profile?id=${employee.id}&action=fired">
                                Уволить
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/profile?id=${employee.id}&action=remove">
                                Удалить все данные
                            </a>
                        </li>
                    </ul>
                </c:if>
            </div>
        </div>
        <br>

        <c:if test="${fired == true}">
            <div class="fired">Уволен</div>
        </c:if>
        <c:if test="${isReturn == true}">
            <div class="return">Возвращён</div>
        </c:if>

        <div class="cards">
            <div class="profile_card">
                <div class="photo">
                    <img class="img" src="<c:url value="/images/man_min.png"/>" alt="man">
                </div>

                <div class="position">${employee.position.name}</div>
                <div class="text">
                    <%
                        Employee emp = (Employee) request.getAttribute("employee");
                        SimpleDateFormat formatter = new SimpleDateFormat("d MMMM yyyy");
                        out.println("Работает с <span class=\"date\">" + formatter.format(emp.getHired()) + "</span>");
                    %>
                </div>
                <%
                    if (emp.getHobby() != null) {
                        out.println("<div class=\"text\">" + emp.getHobby() + "</div>");
                    }
                    if (emp.getBirthday() != null) {
                        formatter = new SimpleDateFormat("d MMMM");
                        out.print("<div class=\"text\">День рождения ");
                        out.println("<span class=\"date\">" + formatter.format(emp.getBirthday()) + "</span></div>");
                    }
                %>
            </div>

            <div class="profile_card">
                <div class="subtitle">Контакты</div>
                <div class="contact"><span class="material-icons contacts">phone</span>${employee.phone}</div>
                <div class="contact"><span class="material-icons contacts">email</span>${employee.email}</div>

                <c:if test="${employee.messengerName != null}">
                    <div class="contact">
                        <span class="material-icons contacts">chat</span>${employee.messengerName}
                        <span class="messenger">${employee.messenger.name}</span>
                    </div>
                </c:if>
            </div>

            <div class="profile_card">
                <div class="subtitle">Задачи</div>
                <c:if test="${isEmpty == false}">
                    <c:forEach var="task" items="${tasks}">
                        <div class="task">${task.name}</div>
                        <div class="desc">${task.description}</div>
                    </c:forEach>
                </c:if>
                <c:if test="${isEmpty == true}">
                    <div class="nothing">Пока задач нет</div>
                </c:if>
            </div>
        </div>

        <script src="<c:url value="/js/scriptProfile.js"/>"></script>
        <script src="<c:url value="/js/scriptRedirect.js"/>"></script>
        <script>
            $(document).ready(function() {
                $('select').niceSelect();
            });
        </script>
    </body>
</html>
