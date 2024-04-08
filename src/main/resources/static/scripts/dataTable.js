$(document).ready(function() {
    $('#all-table').DataTable({
        "processing": true,
        // "responsive": true, // Enable the responsive option
        "ajax": {
            "url": "http://localhost:8888/stock/datatable",
            "type": "GET",
            "dataSrc": ""
        },
        "columns": [
            { "data": "name", "width": "30%" },
            { "data": "type", "width": "20%" },
            { "data": "amount", "width": "12%" },
            { "data": "productiondate", "width": "13%" },
            { "data": "expireDate", "width": "13%" },
            { "data": "daysLeft", "width": "12%" }
        ]
    });
});
