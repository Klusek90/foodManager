$(document).ready(function() {
    $('#all-table').DataTable({
        "processing": true,
        "responsive": true, // Enable the responsive option
        "ajax": {
            "url": "http://localhost:8080/stock/datatable",
            "type": "GET",
            "dataSrc": ""
        },
        "columns": [
            { "data": "name" },
            { "data": "type" },
            { "data": "weight" },
            { "data": "cost" },
            { "data": "other" }
        ]
    });
});
