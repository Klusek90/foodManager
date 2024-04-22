$(document).ready(function() {
    const currentUrl = window.location.href;
    // console.log(currentUrl);
    const url= currentUrl.replace('total', 'datatable')

    $('#all-table').DataTable({
        "processing": true,
        // "responsive": true, // Enable the responsive option
        "ajax": {
            "url": url,
            "type": "GET",
            "dataSrc": ""
        },
        "columns": [
            { "data": "name", "width": "30%" },
            { "data": "type", "width": "10%" },
            { "data": "amount", "width": "10%" },
            { "data": "productiondate", "width": "15%" },
            { "data": "expireDate", "width": "15%" },
            { "data": "daysLeft", "width": "10%" }
        ]
    });
});
