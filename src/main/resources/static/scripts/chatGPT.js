// Add event listener to table rows
// Set to store selected names
let selectedNamesSet = [];




// Add event listener to table rows
document.querySelectorAll('#expiresTable tbody tr').forEach(row => {
    row.addEventListener('click', function() {
        // Get the name of the clicked row
        let name = row.cells[0].textContent;

        // Check if the name is already selected
        if (!selectedNamesSet.includes(name)) {
            // Add the name to the set and table
            selectedNamesSet.push(name);
            let selectedNamesTable = document.getElementById('selectedNamesTable');
            let newRow = selectedNamesTable.insertRow();
            let cell1 = newRow.insertCell(0);
            let cell2 = newRow.insertCell(1);
            cell1.textContent = name;
            cell2.innerHTML = '<button class="btn btn-danger btn-sm">X</button>';
            cell2.querySelector('button').addEventListener('click', function() {
                let index = selectedNamesSet.indexOf(name);
                if (index !== -1) {
                    selectedNamesSet.splice(index, 1); // Remove name from set
                }
                selectedNamesTable.deleteRow(newRow.rowIndex); // Remove row from table
            });
        } else {
            alert("Name '" + name + "' is already selected!");
        }
    });
});

const getData = async (url = '', params = {}) => {
    const queryParams = new URLSearchParams();

    for (const key in params) {
        if (Array.isArray(params[key])) {
            params[key].forEach(value => {
                queryParams.append(key, value);
            });
        } else {
            queryParams.append(key, params[key]);
        }
    }

    const response = await fetch(`${url}?${queryParams.toString()}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
    });
    return response.json();
};

$('#chatqueryButton').on('click', function (e) {
    getData('/request', {
        selectedNamesSet
    }).then(data => {
        $('#answer').text(data)
        console.log(data);
    });
    $('#prompt').modal('show');
});


