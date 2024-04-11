$(document).ready(function() {
    let chart;
    let totalValue =$("#TotalValue");

    function initChart(data, chartType) {
        if (chart) { // If chart already exists, destroy it before creating a new one
            chart.destroy();
        }
        chart = new Chart("myChart", {
            type: chartType,
            data: {
                labels: data.labels,
                datasets: data.datasets
            },
            options: data.options || {}
        });
    }

    $("#monthBtn").click(function() {

        $.ajax({
            url: '/monthSale', // Adjust if you have a different base path
            type: 'GET',
            dataType: 'json', // Expecting JSON response
            success: function(response) {
                var monthData = {
                    labels: response.labels,
                    datasets: [{
                        data: response.dataset,
                        borderColor: "blue",
                        fill: false
                    }]
                };
                initChart(monthData, "line");
            },
            error: function(xhr, status, error) {
                console.error("Error fetching data: ", error);
            }
        });
    });

    $("#weekBtn").click(function() {
        $.ajax({
            url: '/weekSale', // Adjust if you have a different base path
            type: 'GET',
            dataType: 'json', // Expecting JSON response
            success: function(response) {
                var monthData = {
                    labels: response.labels,
                    datasets: [{
                        data: response.dataset,
                        borderColor: "green",
                        fill: false
                    }]
                };
                initChart(monthData, "line");
            },
            error: function(xhr, status, error) {
                console.error("Error fetching data: ", error);
            }
        });
    });

    $("#dayBtn").click(function() {
        $.ajax({
            url: '/dailySale', // Adjust to your actual endpoint
            type: 'GET',
            dataType: 'json', // Expecting JSON response
            success: function(response) {
                // The response is an object, not an array
                let labels = Object.keys(response); // Gets all recipe names (keys of the object)
                let quantities = Object.values(response); // Gets all quantities (values of the object)

                let totalSales = quantities.reduce((acc, curr) => acc + curr, 0);
                // Create an array with the total sales for each item
                let totalSalesArray = Array(labels.length).fill(totalSales);


                let dayData = {
                    labels: labels,
                    datasets: [{
                        data: quantities,
                        backgroundColor: [
                            'rgba(255, 99, 132, 0.2)',
                            'rgba(54, 162, 235, 0.2)',
                            'rgba(255, 206, 86, 0.2)',
                            'rgba(75, 192, 192, 0.2)',
                            'rgba(153, 102, 255, 0.2)',
                            'rgba(255, 159, 64, 0.2)',
                            'rgba(199, 199, 199, 0.2)'
                        ],
                        borderColor: [
                            'rgba(255,99,132,1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(255, 206, 86, 1)',
                            'rgba(75, 192, 192, 1)',
                            'rgba(153, 102, 255, 1)',
                            'rgba(255, 159, 64, 1)',
                            'rgba(199, 199, 199, 1)'
                        ],
                        borderWidth: 1
                    }, {
                        data: totalSalesArray,
                        backgroundColor: 'rgba(0, 0, 0, 0)', // Transparent color
                        borderColor: 'rgba(0, 0, 0, 0)', // Transparent color
                        borderWidth: 0
                    }]
                };
                initChart(dayData, "doughnut");
                totalValue.text(totalSales +"£")

            },
            error: function(xhr, status, error) {
                console.error("Error fetching recipe sales data: ", error);
            }
        });

    });
});
