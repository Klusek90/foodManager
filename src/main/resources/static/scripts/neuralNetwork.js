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
            // Clear previous responses
            $('#respondWindow').empty();
            // Standardize the response format
            console.log(data)
            const meals = parseResponse(data);
            // Display the standardized response
            meals.forEach((meal, index) => {
                // Create a paragraph for each meal
                $('<h6>').addClass('mt-2 bold').text("Meal " + (index + 1)).appendTo('#respondWindow');
                $('<p>').addClass('mb-3').text(meal).appendTo('#respondWindow');
            });
        }).catch(error => {
            // Handle errors
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
        // Check if each item in the array is a string, if so, it's already in the desired format
        if (data.every(item => typeof item === 'string')) {
            meals = data;
        } else {
            // If not, try to parse the response as JSON
            try {
                meals = JSON.parse(data);
                // Check if the parsed data is an array of strings, if not, handle as needed
                if (!Array.isArray(meals) || !meals.every(item => typeof item === 'string')) {
                    // Handle unexpected format, e.g., convert to strings
                    meals = meals.map(item => String(item));
                }
            } catch (error) {
                console.error("Error parsing response:", error);
                meals = ["Error parsing response"];
            }
        }
    } else {
        // Handle unexpected format, e.g., convert to string
        meals.push(String(data));
    }
    return meals;
}



