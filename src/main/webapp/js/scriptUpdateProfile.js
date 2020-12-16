function save(id) {
    let surname = $("#surname").val();
    let name = $("#name").val();
    let patronymic = $("#patronymic").val();
    let sex = $("input[name='sex']:checked").val();

    let position = $("#position").val();
    let salary = $("#salary").val();
    let birthday = $("#birthday").val();

    let hired = $("#hired").val();
    let hobby = $("#hobby").val();

    let phone = $("#phone").val();
    let email = $("#email").val();
    let messenger = $("#messenger").val();
    let messengerId = $("#messengerType").val();

    let arr = [surname, name, patronymic, position, salary, hired, phone, email];

    if (birthday === "")  birthday = null;
    if (hobby === "")     hobby = null;
    if (messenger === "") messenger = null;

    let employee = {
        name: name,
        surname: surname,
        patronymic: patronymic,
        email: email,
        phone: phone,
        sex: sex,
        salary: salary,
        hobby: hobby,
        messengerName: messenger,
        hired: hired,
        fired: null,
        birthday: birthday
    }

    if (isWordsNotEmpty(arr)) {
        $.ajax({
            url: "/profile/edit?id=" + id,
            method: "POST",
            data: {
                "id": id,
                "employee": JSON.stringify(employee),
                "position": position,
                "messengerId": messengerId
            },
            success: function () {
                location.reload();
            }
        });
    }
}

$(document).ready(function() {
    $('select').niceSelect();

    $("#name").blur(function() { checkFillInput($(this)); });
    $("#surname").blur(function() { checkFillInput($(this)); });
    $("#patronymic").blur(function() { checkFillInput($(this)); });
    $("#position").blur(function() { checkFillInput($(this)); });
    $("#salary").blur(function() { checkFillInput($(this)); });
    $("#phone").blur(function() { checkFillInput($(this)); });
    $("#email").blur(function() { checkFillInput($(this)); });
    $("#hired").blur(function() { checkFillInput($(this)); });
});

function checkFillInput(element) {
    if(!element.val()) {
        element.addClass("error");
    } else {
        element.removeClass("error");
    }
}

function isWordsNotEmpty(arr) {
    for(let i = 0; i < arr.length; i++) {
        if(arr[i] === "" || arr[i] == null) {
            return false;
        }
    }
    return true;
}