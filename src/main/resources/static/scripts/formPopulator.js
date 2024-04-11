$(document).ready(function() {
    $('#itemsList').on('click', 'li', function() {
        $('.showform').css('display', 'block');
        let itemName = $(this).text();
        let itemNumber = $(this).attr('id').substring($(this).attr('id').indexOf('-') + 1);
        let index = parseInt(itemNumber) + 1;

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
            $row.append('<td><span>' + itemName + '</span></td><td><input type="hidden" name="productIds" value="' + index + '"/></td><td><input required name="quantities" min="0" type="number"></td><td><button class="remove-item btn btn-danger">X</button></td>');

            $('#targetTable tbody').append($row);
        }
    });

    // Add click event listener for remove buttons
    $('#targetTable').on('click', '.remove-item', function() {
        $(this).closest('tr').remove();
    });
});