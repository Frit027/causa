getTable();

$(document).ready(function() {
    $('select').niceSelect();
});

const dialog = document.querySelector('dialog');

function getTable() {
    $.ajax({
        url: "/tasks_table",
        dataType: "json",
        method: "GET",
        success: function(data) {
            setData(data);
        }
    });
}

function getSortTasks() {
    $.ajax({
        url: "/tasks_table",
        dataType: "json",
        method: "GET",
        data: {"action": "SORT"},
        success: function(data) {
            setData(data);
        }
    });
}

$("#confirm").on("click", function() {
    let name = $("#name").val();
    let executor = $("#executor").val();
    let description = $("#description").val();
    let deadline = $("#deadline").val();
    let priority = $("#priority").val();

    let arr = [name, executor, description, deadline, priority]

    if (checkEmpty(arr)) {
        $.ajax({
            url: "/tasks",
            method: "POST",
            data: {
                "name": name,
                "executor": executor,
                "description": description,
                "deadline": deadline,
                "priority": priority
            },
            success: function () {
                dialog.close();
                getTable();
            }
        });
    }
});

function taskComplete(id, name) {
    let text = `Задача будет завершена сотрудником <b>${name}</b>`;
    $("#pExecutor").html(text);

    $("#setBonusButton").attr("value", id);
    $("#bonus_5").attr("value", 5);
    $("#bonus_10").attr("value", 10);
    $("#bonus_15").attr("value", 15);

    $("#taskModal").css("display", "block");
}

function setData(data) {
    $("tbody").empty();

    let countClick = data[data.length - 1].countClick;
    if (countClick % 3 === 0) {
        $("#priorityTitle").text("Приоритет ⇑");
    } else if (countClick % 3 === 1) {
        $("#priorityTitle").text("Приоритет ⇓");
    } else {
        $("#priorityTitle").text("Приоритет ⇕");
    }

    data.forEach(element => {
        if (element.name !== undefined) {
            addRow(element);
        }
    });
}

function addRow(task) {
    let checkMark = `<input id="c1" type="checkbox" onclick="taskComplete(${task.id}, '${task.executor}')"><label for="c1"/>`;
    let str = `<tr>
                    <td>${task.name}</td>
                    <td>${task.deadline}</td>
                    <td>${task.executor}</td>
                    <td>${task.description}</td>
                    <td>${task.priority}</td>
                    <td class="tdCheckMark">${checkMark}</td>
               </tr>`;

    $("tbody").append(str);
}

function checkEmpty(arr) {
    for(let i = 0; i < arr.length; i++) {
        if(arr[i] === "" || arr[i] == null) return false;
    }
    return true;
}

$("#buttonShowDialog").on("click", function() {
    $(":input").val('');
    dialog.showModal();
});

$("#cancel").on("click", function() {
    dialog.close();
});

$("#close").on("click", function() {
    $("input[type=checkbox]").prop('checked', false);
    $("#taskModal").css("display", "none");
});
