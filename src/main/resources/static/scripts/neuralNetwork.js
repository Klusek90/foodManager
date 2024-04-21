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
    $('#respondWindow').text("");
    if (selectedNamesSet.length < 1) {
        alert("You need to select some products");
    } else {
        selectedNamesSet.push($('#additionalPrompt').val());
        getData('/request', {
            selectedNamesSet
        }).then(data => {
            $('#respondWindow').empty();
            const meals = parseResponse(data);
            meals.forEach((meal, index) => {
                $('<h6>').addClass('mt-2 bold').text("Meal " + (index + 1)).appendTo('#respondWindow');
                $('<p>').addClass('mb-3').text(meal).appendTo('#respondWindow');
            });
        }).catch(error => {
            console.error("Error:", error);
            $('#respondWindow').text("An error occurred while fetching data");
        });
        $('#prompt').modal('show');
    }
});

// Function to parse and standardize the response
function parseResponse(data) {
    let meals = [];
    if (Array.isArray(data)) {
        data.forEach(item => {
            if (typeof item === 'object' && item.meal && typeof item.meal === 'string' && item.description && typeof item.description === 'string') {
                meals.push(item.meal + ": " + item.description); // Combine meal name and description
            } else {
                console.error("Unexpected item format:", item);
                // Optionally add a fallback or error message
                meals.push("Invalid meal data");
            }
        });
    } else {
        console.error("Error: Data is not an array", data);
        meals.push(String(data));
    }
    return meals;
}



