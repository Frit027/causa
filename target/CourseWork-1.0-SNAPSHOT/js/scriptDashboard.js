getPosition();

const taskCard = $('#card_tasks');
const birthdayCard = $('#card_birthdays');

taskCard.draggable({
    handle: '.title',
    stop: onTasksCardStop
});

birthdayCard.draggable({
    handle: '.title',
    stop: onBirthdayCardStop
});

function onTasksCardStop() {
    let position = taskCard.position();
    sendPosition("task", position.top, position.left);
}

function onBirthdayCardStop() {
    let position = birthdayCard.position();
    sendPosition("birthday", position.top, position.left);
}

function getPosition() {
    $.ajax({
        url: "/position",
        dataType: "json",
        method: "GET",
        success: function(data) {
            taskCard.css({top: data[0].top + 'px', left: data[0].left + 'px', position:'absolute'});
            birthdayCard.css({top: data[1].top + 'px', left: data[1].left + 'px', position:'absolute'});
            getCountTasks();
        }
    });
}

function sendPosition(id, top, left) {
    $.ajax({
        url: "/position",
        method: "POST",
        data: {
            "id": id,
            "top": top,
            "left": left
        }
    });
}

function getCountTasks() {
    $.ajax({
        url: "/dashboard?request=countTasks",
        dataType: "json",
        method: "GET",
        success: function(data) {
            $(".count").text(data);
            let height = taskCard.height();
            height = data * 35 + height;
            taskCard.css("height", height + "px");
            getBirthdays();
        }
    });
}

function getBirthdays() {
    $.ajax({
        url: "/dashboard?request=birthdays",
        dataType: "json",
        method: "GET",
        success: function(data) {
            setData(data);
        }
    });
}

function setData(data) {
    data.forEach(element => {
        if (element != null) {
            let height = birthdayCard.height();
            height += 85;
            birthdayCard.css("height", height + "px");
            jQuery('<div/>', {
                id: element.id,
                "class": 'person'
            }).appendTo('#birthdays');

            createDiv(element.name, element.id, "name");
            createDiv(element.position, element.id, "position");
            createDiv(element.left, element.id, "when");
            addSpan(getAgeText(element.age));
        }
    });
}

function createDiv(text, id, cl) {
    jQuery('<div/>', {
        text: text,
        "class": cl
    }).appendTo("#" + id);
}

function addSpan(age) {
    $(".when").last().append(`<span class="birthday"> ${age}</span>`);
}

function getAgeText(age) {
    let text;
    let count = age % 100;
    if (count >= 5 && count <= 20) {
        text = 'лет';
    } else {
        count = count % 10;
        if (count === 1) {
            text = 'год';
        } else if (count >= 2 && count <= 4) {
            text = 'года';
        } else {
            text = 'лет';
        }
    }
    return age + " " + text;
}
