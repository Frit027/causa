function getDataForDiagram() {
    $.ajax({
        url: "/finance?request=diagram",
        dataType: "json",
        method: "GET",
        success: function(data) {
            parseData(JSON.parse(data));
        }
    });
}

let names = [];
let premiums = [];
let countBonuses = [];
let countFines = [];
function parseData(data) {
    data.premium.forEach(emp => {
        names.push(emp.surname + " " + emp.name);
        premiums.push(emp.premium);
    });
    data.bonus.forEach(emp => {
        countBonuses.push(emp.countBonuses);
    });
    data.fine.forEach(emp => {
        countFines.push(emp.countFines);
    });

    drawDiagrams();
}

const canvasPremium = $("#diagramPremium");
const canvasBonus = $("#diagramBonus");
const canvasFine = $("#diagramFine");
function drawDiagrams() {
    let barPremium = new Chart(canvasPremium, {
        type: 'bar',
        data: {
            labels: names,
            datasets: [{
                label: 'Премия',
                data: premiums,
                backgroundColor: [
                    'rgba(255, 99, 132, 0.6)',
                    'rgba(255, 99, 132, 0.6)',
                    'rgba(255, 99, 132, 0.6)',
                    'rgba(255, 99, 132, 0.6)',
                    'rgba(255, 99, 132, 0.6)'
                ]
            }]
        },
        options: {
            responsive: false
        }
    });

    let barBonus = new Chart(canvasBonus, {
        type: 'bar',
        data: {
            labels: names,
            datasets: [{
                label: 'Количество бонусов',
                data: countBonuses,
                backgroundColor: [
                    'rgba(255, 206, 86, 0.6)',
                    'rgba(255, 206, 86, 0.6)',
                    'rgba(255, 206, 86, 0.6)',
                    'rgba(255, 206, 86, 0.6)',
                    'rgba(255, 206, 86, 0.6)'
                ]
            }]
        },
        options: {
            responsive: false
        }
    });

    let barFine = new Chart(canvasFine, {
        type: 'bar',
        data: {
            labels: names,
            datasets: [{
                label: 'Количество штрафов',
                data: countFines,
                backgroundColor: [
                    'rgba(153, 102, 255, 0.6)',
                    'rgba(153, 102, 255, 0.6)',
                    'rgba(153, 102, 255, 0.6)',
                    'rgba(153, 102, 255, 0.6)',
                    'rgba(153, 102, 255, 0.6)'
                ]
            }]
        },
        options: {
            responsive: false
        }
    });
}