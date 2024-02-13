$(document).ready(function() {
    $('#all-table').DataTable({
        "processing": true,
        "ajax": {
            "url": "http://localhost:8080/stock/datatable",
            "type": "GET",
            // Ensure proper data handling, assuming your endpoint returns a plain array
            "dataSrc": ""
        },
        "columns": [
            {data: "name", "width": "20%"},
            {data: "type", "width": "20%"},
            {data: "weight", "width": "20%"},
            {data: "cost", "width": "20%"},
            {data: "other", "width": "20%"}
        ]
    });
});
