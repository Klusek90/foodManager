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
            // Add the name to the set and list
            selectedNamesSet.push(name);
            let selectedNamesList = document.getElementById('selectedNames');
            let listItem = document.createElement('li');
            listItem.textContent = name;
            listItem.classList.add('list-group-item');
            selectedNamesList.appendChild(listItem);
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


