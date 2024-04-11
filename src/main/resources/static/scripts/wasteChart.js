$(document).ready(function() {
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
        totalValue.css('color','blue');
        selectedDate = dataPicker.val();

        //btn highlight
        todayBtn.css('background', '#0d6efd')
        weekBtn.css('background', '#0d6efd')
        monthBtn.css('background', 'orange')

        $.ajax({
            url: '/monthWaste/'+selectedDate, // Adjust if you have a different base path
            type: 'GET',
            dataType: 'json', // Expecting JSON response
            success: function(response) {

                //total sale
                totalWastege = response.dataset.reduce((acc, curr) => acc + curr, 0);

                let monthData = {
                    labels: response.labels,
                    datasets: [{
                        data: response.dataset,
                        borderColor: "blue",
                        fill: false
                    }],
                    options: {
                        legend: {display: false},
                        title: {display:true, text:" Monthly sale"}
                    }
                };
                initChart(monthData, "line");
                totalValue.text("Total Wastage:  "+ totalWastege);
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

        selectedDate = dataPicker.val();
        totalWastege=0;
        $.ajax({
            url: '/weekWaste/'+ selectedDate, // Adjust if you have a different base path
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
                        borderColor: "green",
                        fill: false
                    }],
                    options: {
                        legend: {display: false },
                        title: {display:true, text:" Weekly sale"}
                    }
                };
                initChart(monthData, "line");
                totalValue.text("Total Wastage:  "+ totalWastege);
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
        $.ajax({
            url: '/dailyWaste/'+ selectedDate, // Adjust to your actual endpoint
            type: 'GET',
            dataType: 'json', // Expecting JSON response
            success: function(response) {
                // Merge meals with the same name
                let mergedMeals = {};
                for (let key in response) {
                    let meal = response[key];
                    if (mergedMeals.hasOwnProperty(meal.name)) {
                        mergedMeals[meal.name].quantity += meal.quantity;
                        mergedMeals[meal.name].price += meal.price;
                    } else {
                        mergedMeals[meal.name] = meal;
                    }
                }
                //total sale
                for (let key in response) {
                    let meal = response[key];
                    totalSalePrice += meal.quantity * meal.price;
                }

                // Extract merged meal data
                let labels = [];
                let quantities = [];
                for (var name in mergedMeals) {
                    labels.push(name);
                    quantities.push(mergedMeals[name].quantity);
                }

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
                    }],
                    options: {
                        title: {display:true, text:" Daily sale"},
                        legend: {display: false}
                    }
                };
                initChart(dayData, "doughnut");
                totalValue.text("Total Wastage:  "+ totalWastege);

            },
            error: function(xhr, status, error) {
                console.error("Error fetching recipe sales data: ", error);
            }
        });
    });
});
