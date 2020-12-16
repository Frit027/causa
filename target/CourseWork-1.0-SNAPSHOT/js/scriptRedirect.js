function redirectToDashboard(){
    setTimeout(redirect, 600, "/dashboard");
}

function redirectToTasks(){
    setTimeout(redirect, 600, "/tasks");
}

function redirectToEmployees(){
    setTimeout(redirect, 600, "/employees");
}

function redirectToFinance(){
    setTimeout(redirect, 600, "/finance");
}

function redirect(url) {
    $(location).attr('href', url);
}