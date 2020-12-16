setWords();
getBudget();

function updateBudget() {
    let budget = $("#inputBudget").val();
    let id = $(".budget").attr("id");
    if (isInteger(budget)) {
        $.ajax({
            url: "/finance?request=finance",
            method: "POST",
            data: {
                "id": id,
                "budget": budget
            },
            success: function (data) {
                $("#inputBudget").val('');
                setBudgetIncome(data.id, data.budget, data.income);
            }
        });
    }
}

function getBudget() {
    $.ajax({
        url: "/finance?request=finance",
        dataType: "json",
        method: "GET",
        success: function(data) {
            if (data.length === 0) {
                $("#setBudgetButton").text("Установить бюджет");
            } else {
                setBudgetIncome(data.id, data.budget, data.income);
                $("#setBudgetButton").text("Изменить бюджет");
            }
            getSalary();
        }
    });
}

function getSalary() {
    $.ajax({
        url: "/finance?request=salary",
        dataType: "json",
        method: "GET",
        success: function(data) {
            $("#salaries").text(data[0].toFixed(2) + " ₽");
            $("#premiums").text(data[1].toFixed(2) + " ₽");
            $("#total").text((data[0] + data[1]).toFixed(2));
            getDataForDiagram();
        }
    });
}

function setBudgetIncome(id, budget, income) {
    $(".budget").attr("id", id)
                .text(budget + " ₽");
    $("#income").text(income.toFixed(2) + " ₽");
}

function showPopUp() {
    $("#myPopup").toggleClass("show");
}

function setWords() {
    let word = declOfNum($("#days").text());
    if (word === "день") {
        $("#left").text("остался");
    }
    $("#wordDays").text(word);
}

function declOfNum(number) {
    let words = ["день", "дня", "дней"];
    return words[(number % 100 > 4 && number % 100 < 20)
            ? 2 : [2, 0, 1, 1, 1, 2][(number % 10 < 5) ? number % 10 : 5]];
}

function isInteger(number) {
    if (number === "") return false;
    let decimal=  /[0-9]+/;
    if (number.match(decimal) == null) return false;
    return number.match(decimal)[0] === number;
}