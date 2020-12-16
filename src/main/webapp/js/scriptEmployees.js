getTable();

const dialog = document.querySelector('dialog');
const tbody = $("tbody");
$("#menuItem").css("text-decoration", "underline");
let dataJson;

function getTable() {
    $.ajax({
        url: "/employees_table?request=employees",
        dataType: "json",
        method: "GET",
        success: function(data) {
            dataJson = data;
            $("#countWorking").text(dataJson.length);
            getFilters();
        }
    });
}

function getFilters() {
    $.ajax({
        url: "/employees_table?request=filters",
        dataType: "json",
        method: "GET",
        success: function(data) {
            setChecked(data);
            createNamesColumns(data);
            fillTable(data);
        }
    });
}

function setChecked(filters) {
    filters.forEach(filter => {
        $("#" + filter + "Checkbox").prop('checked', true);
    });
}

function fillTable(filters) {
    tbody.empty();
    tbody.empty();
    let str = `<tr class="empty_row"><td rowspan="0"/></tr>`;
    tbody.append(str);

    let map = new Map();
    dataJson.forEach(emp => {
        let tr = `<tr>`;

        map.set("name", `<td><a class="nameEmployee" href="/employees?id=${emp.id}">${emp.fullName}</a></td>`)
        map.set("position", `<td>${emp.position}</td>`)
        map.set("email", `<td>${emp.email}</td>`)
        map.set("phone", `<td>${emp.phone}</td>`)
        map.set("sex", `<td>${emp.sex}</td>`)
        map.set("hired", `<td>${emp.hired}</td>`)
        map.set("salary", `<td>${emp.salary}</td>`)
        map.set("birthday", `<td>${emp.birthday}</td>`)

        filters.forEach(filter => {
            tr += map.get(filter);
        });
        tbody.append(tr + `</tr>`);
    });
}

$("input[type=checkbox]").on( "click", function() {
    let arr = [];
    $("input:checkbox:checked").each(function() {
        let filter = $(this).attr('id').slice(0, -8);
        arr.push(filter);
    });

    sendFilters(arr);
});

function sendFilters(arr) {
    $.ajax({
        url: "/employees_table",
        method: "POST",
        data: { filters: arr },
        success: function () {
            getFilters();
        }
    });
}

function createNamesColumns(filters) {
    $(".remove").remove();
    filters.forEach(filter => {
        switch (filter) {
            case "name":
                addName("Сотрудник");
                break;
            case "position":
                addName("Должность");
                break;
            case "email":
                addName("Почта");
                break;
            case "phone":
                addName("Телефон");
                break;
            case "sex":
                addName("Пол");
                break;
            case "hired":
                addName("День приёма на работу");
                break;
            case "salary":
                addName("Зарплата");
                break;
            case "birthday":
                addName("День рождения");
        }
    });

}

function addName(name) {
    let th = `<th class="remove">${name}</th>`;
    $("thead").find("tr").append(th);
}

$("#confirm").on("click", function() {
    let surname = $("#surname").val();
    let name = $("#name").val();
    let patronymic = $("#patronymic").val();
    let email = $("#email").val();
    let phone = $("#phone").val();
    let sex = $("input[name='sex']:checked").attr('id');
    let date = $("#hired").val();
    let position = $("#position").val();
    let salary = $("#salary").val();

    let arr = [surname, name, patronymic, email, phone, sex, date, position, salary];

    if (checkEmpty(arr)) {
        $.ajax({
            url: "/employees",
            method: "POST",
            data: {
                "surname": surname,
                "name": name,
                "patronymic": patronymic,
                "email": email,
                "phone": phone,
                "sex": sex,
                "hired": date,
                "position": position,
                "salary": salary
            },
            success: function () {
                dialog.close();
                getTable();
            }
        });
    }
});

$("#button").on("click", function() {
    $(":input").val('');
    dialog.showModal();
});

$("#cancel").on("click", function() {
    dialog.close();
});

let expanded = false;
function showCheckboxes() {
    if (!expanded) {
        $("#checkboxes").css("display", "block");
        expanded = true;
    } else {
        $("#checkboxes").css("display", "none");
        expanded = false;
    }
}

function checkEmpty(arr) {
    for(let i = 0; i < arr.length; i++) {
        if(arr[i] === "" || arr[i] == null) {
            return false;
        }
    }
    return true;
}