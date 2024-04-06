$(document).ready(function() {



    $('#itemsList').on('click', 'li', function() {
        $('.showform').css('display', 'block');
        var itemName = $(this).text();

        // Check if the item is already added
        var isItemAdded = $('#targetTable tbody tr').filter(function() {
            return $(this).find('td:first').text() === itemName;
        }).length > 0;

        if (isItemAdded) {
            // Item is already in the list, show an alert
            alert('Item already on the list');
        } else {
            // Calculate the position based on the current number of items
            var position = $('#targetTable tbody tr').length; // This will start from 0 for the first item

            // Item is not in the list, add a new row
            var $row = $('<tr>');
            $row.append($('<td>').text(itemName));

            var $nameInput = $('<input>').attr({
                type: 'hidden',
                name: 'products[' + position + '].name',
                value: itemName.replace(/\s+/g, '_').toLowerCase()
            });
            $row.append($nameInput);

            var $quantityInput = $('<input>').attr({
                type: 'number',
                value: 0,
                class: 'form-control',
                name: 'products[' + position + '].quantity'
            });
            $row.append($('<td>').append($quantityInput));

            $('#targetTable tbody').append($row);
        }
    });
});
