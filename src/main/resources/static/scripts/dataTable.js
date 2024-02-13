$(document).ready(function() {
    $('#all-table').DataTable({
        "processing": true,
        "serverSide": true,
        "ajax": {
            "url": "/stock/datatable",
            "type": "GET",
        },
        "order": [[0, "desc"]],
        "columns": [
            {data: "name", "width": "20%"},
            {data: "type", "width": "20%"},
            {data: "weight", "width": "20%"},
            {data: "cost", "width": "20%"},
            {data: "other", "width": "20%"}
        ]
    });

});
