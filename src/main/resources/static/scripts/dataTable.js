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
            {data: "name", "width": "15%"},
            {data: "type", "width": "15%"},
            {data: "weight", "width": "15%"},
            {data: "cost", "width": "15%"},
            {data: "other", "width": "15%"}
        ]
    });
});
