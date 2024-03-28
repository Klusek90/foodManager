$(document).ready(function() {



    $('#itemsList').on('click', 'li', function() {

        $('.wastform').css('display', 'block');
        var itemName = $(this).text();

        // Check if the item is already added
        var isItemAdded = $('#targetTable tbody tr').filter(function() {
            return $(this).find('td:first').text() === itemName;
        }).length > 0;

        if (isItemAdded) {
            // Item is already in the list, show an alert
            alert('Item already on the list');
        } else {
            // Item is not in the list, add a new row
            var $row = $('<tr>');
            $row.append($('<td>').text(itemName));

            var $input = $('<input>').attr({
                type: 'number',
                value: 0,
                class: 'form-control',
                name: 'quantity_' + itemName.replace(/\s+/g, '_').toLowerCase()
            });
            $row.append($('<td>').append($input));

            $('#targetTable tbody').append($row);
        }
    });
});
