$(document).ready(function() {
    const currentUrl = window.location.href;
    let chart;
    let totalValue =$("#TotalValue");
    let totalWastege = 0;
    let dataPicker=$('#datePicker');
    let selectedDate;

    //buttons
    let todayBtn = $("#dayBtn");
    let weekBtn = $("#weekBtn");
    let monthBtn = $("#monthBtn");

    //today date
    let today = new Date();
    let dd = String(today.getDate()).padStart(2, '0');
    let mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
    let yyyy = today.getFullYear();

    today = yyyy + '-' + mm + '-' + dd;
    dataPicker.val(today);

    function initChart(data, chartType) {
        $('.showform').css('display', 'block');
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
        totalWastege =0;
        totalValue.css('color','orange');
        selectedDate = dataPicker.val();

        //btn highlight
        todayBtn.css('background', '#0d6efd')
        weekBtn.css('background', '#0d6efd')
        monthBtn.css('background', 'orange')
        let url = currentUrl.replace('reports/waste', 'monthWaste/');
        $.ajax({
            url: url +selectedDate, // Adjust if you have a different base path
            type: 'GET',
            dataType: 'json', // Expecting JSON response
            success: function(response) {

                //total sale
                totalWastege = response.dataset.reduce((acc, curr) => acc + curr, 0);

                let monthData = {
                    labels: response.labels,
                    datasets: [{
                        data: response.dataset,
                        borderColor: "orange",
                        fill: false
                    }],
                    options: {
                        legend: {display: false},
                        title: {display:true, text:" Monthly sale"}
                    }
                };
                initChart(monthData, "line");
                totalValue.text("Total Wastage:  "+ totalWastege +" g");
            },
            error: function(xhr, status, error) {
                console.error("Error fetching data: ", error);
            }
        });
    });

    $("#weekBtn").click(function() {
        //btn highlight
        todayBtn.css('background', '#0d6efd')
        weekBtn.css('background', 'orange')
        monthBtn.css('background', '#0d6efd')

        totalValue.css('color','red')
        selectedDate = dataPicker.val();
        totalWastege=0;
        let url = currentUrl.replace('reports/waste', 'weekWaste/');
        $.ajax({
            url: url + selectedDate, // Adjust if you have a different base path
            type: 'GET',
            dataType: 'json', // Expecting JSON response
            success: function(response) {
                // Filter out days with 0 sales
                let filteredLabels = [];
                let filteredDataset = [];

                response.dataset.forEach((sale, index) => {
                    if(sale > 0) { // Only include days with sales > 0
                        filteredLabels.push(response.labels[index]);
                        filteredDataset.push(sale);
                    }
                });

                // Calculate total sales after filtering
                totalWastege = filteredDataset.reduce((acc, curr) => acc + curr, 0);

                let monthData = {
                    labels: filteredLabels,
                    datasets: [{
                        data: filteredDataset,
                        borderColor: "red",
                        fill: false
                    }],
                    options: {
                        legend: {display: false },
                        title: {display:true, text:" Weekly sale"}
                    }
                };
                initChart(monthData, "line");
                totalValue.text("Total Wastage:  "+ totalWastege + " g");
            },
            error: function(xhr, status, error) {
                console.error("Error fetching data: ", error);
            }
        });
    });

    $("#dayBtn").click(function() {

        todayBtn.css('background', 'orange')
        weekBtn.css('background', '#0d6efd')
        monthBtn.css('background', '#0d6efd')

        selectedDate = dataPicker.val();
        totalWastege=0;
        totalValue.css('color','black')
        let url = currentUrl.replace('reports/waste', 'dailyWaste/');
        $.ajax({
            url: url + selectedDate, // Adjust to your actual endpoint
            type: 'GET',
            dataType: 'json', // Expecting JSON response
            success: function(response) {
                // Directly using 'response' assuming it's the object e.g., {Cooked Spaghetti: 23, Cooked Penne: 80, Bolognese Sauce: 234}

                let labels = Object.keys(response); // Get meal names as labels
                let quantities = Object.values(response); // Get quantities as data for the chart

                // Calculate total wastage
                let totalWastage = quantities.reduce((acc, curr) => acc + curr, 0);

                let dayData = {
                    labels: labels,
                    datasets: [{
                        data: quantities,
                        backgroundColor: [
                            'rgba(255, 99, 132, 0.2)',
                            'rgba(54, 162, 235, 0.2)',
                            'rgba(255, 206, 86, 0.2)',
                            'rgba(54, 206, 86, 0.2)',
                            'rgba(255, 259, 286, 0.2)',
                            'rgba(75, 192, 192, 0.2)',
                            'rgba(15, 59, 286, 0.2)',
                            'rgba(100, 159, 286, 0.2)',
                            'rgba(130, 100, 186, 0.2)',
                            'rgba(255, 59, 86, 0.2)',
                            'rgba(10, 259, 186, 0.2)'
                        ],
                        borderColor: [
                            'rgba(255, 99, 132, 1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(255, 206, 86, 1)',
                            'rgba(54, 206, 86, 1)',
                            'rgba(0, 0, 0, 1)',
                            'rgba(75, 192, 192, 1)',
                            'rgba(255, 59, 86, 1)',
                            'rgba(100, 159, 286, 1)',
                            'rgba(130, 100, 186, 1)',
                            'rgba(255, 59, 86, 1)',
                            'rgba(10, 259, 186, 1)'
                        ],
                        borderWidth: 1
                    }]
                };

                // Assuming 'initChart' is a function defined elsewhere to initialize the chart
                initChart(dayData, "doughnut");

                // Assuming 'totalValue' is a jQuery selector for an element where you want to display total wastage
                totalValue.text("Total Wastage: " + totalWastage + " g");
            },
            error: function(xhr, status, error) {
                console.error("Error fetching daily waste data: ", error);
            }
        });


    });
});
