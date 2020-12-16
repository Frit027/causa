const dialog = document.querySelector('dialog');

if (dialog != null) {
    dialog.showModal();
}

function setNewExecutor(id) {
    let newExecutorId = $("#executor").val();

    if (newExecutorId !== "") {
        $.ajax({
            url: "/profile?id=" + id + "&action=fired",
            method: "POST",
            data: {
                "postAction": "SET",
                "newExecutorId": newExecutorId
            },
            success: function () {
                location.reload();
            }
        });
    }
}

function removeTasks(id) {
    $.ajax({
        url: "/profile?id=" + id + "&action=fired",
        method: "POST",
        data: {
            "postAction": "REMOVE"
        },
        success: function () {
            location.reload();
        }
    });
}