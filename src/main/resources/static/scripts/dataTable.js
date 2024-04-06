$(document).ready(function() {
    $('#all-table').DataTable({
        "processing": true,
        // "responsive": true, // Enable the responsive option
        "ajax": {
            "url": "http://localhost:8080/stock/datatable",
            "type": "GET",
            "dataSrc": ""
        },
        "columns": [
            { "data": "name","width": "25%" },
            { "data": "type" ,"width": "25%" },
            { "data": "amount" ,"width": "25%" },
            { "data": "date","width": "25%"  }
        ]
    });
});
