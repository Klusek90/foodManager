// Add event listener to table rows
// Set to store selected names
let selectedNamesSet = new Set();




// Add event listener to table rows
document.querySelectorAll('#expiresTable tbody tr').forEach(row => {
    row.addEventListener('click', function() {
        // Get the name of the clicked row
        let name = row.cells[0].textContent;

        // Check if the name is already selected
        if (!selectedNamesSet.has(name)) {
            // Add the name to the set and list
            selectedNamesSet.add(name);
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




$('#chatqueryButton').on('click', function (e) {

    let additionalPrompt = $('#additionalPrompt').val()
   console.log(additionalPrompt)

    // Perform AJAX request to /request endpoint
    $.ajax({
        url: '/request',
        type: 'GET',
        // data: {
        //     products: products,
        //     additionalPrompt: additionalPrompt,
        //     vegan: isVegan
        // },
        success: function (response) {
            console.log(response); // Log response to the console
        },
        error: function (xhr, status, error) {
            console.error(xhr.responseText); // Log error to the console
        }
    });

    setTimeout(2000)
    // Trigger the modal here
    $('#prompt').modal('show');

});