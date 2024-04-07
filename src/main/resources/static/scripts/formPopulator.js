$(document).ready(function() {
    $('#itemsList').on('click', 'li', function() {
        $('.showform').css('display', 'block');
        let itemName = $(this).text();
        let itemNumber= $(this).attr('id').substring($(this).attr('id').indexOf('-')+1);
        let index = parseInt(itemNumber)+1;

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
            $row.append('<td ><span>'+itemName+'</span></td><td><input type="hidden" name="productIds" value="'+ index+'"/></td><td><input name="quantities" type="number"></td>');

            // var $nameInput = $('<input>').attr({
            //     type: 'hidden',
            //     name: 'products[' + position + '].name',
            //     value: itemName.replace(/\s+/g, '_').toLowerCase()
            // });
            // $row.append($nameInput);
            //
            // var $quantityInput = $('<input>').attr({
            //     type: 'number',
            //     value: 0,
            //     class: 'form-control',
            //     name: 'products[' + position + '].quantity'
            // });
            // $row.append($('<td>').append('<span th:text="chuj">chuj ci w dupe</span>'));

            $('#targetTable tbody').append($row);
        }
    });
});
