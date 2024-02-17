$(document).ready(function () {
    const monthNames = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
    let currentDate = new Date();

    function fillCalendar(month, year) {
        let firstDay = new Date(year, month).getDay();
        let daysInMonth = new Date(year, month + 1, 0).getDate();
        $("#calendarDays").empty();
        $("#monthYear").text(monthNames[month]);
        for (let i = 0; i < firstDay; i++) {
            $("#calendarDays").append('<div class="day"></div>'); // Fill empty days
        }
        for (let day = 1; day <= daysInMonth; day++) {
            let dayElement = $(`<div class="day clickable">${day}</div>`); // Add 'clickable' class or any identifier
            dayElement.click(function() {
                $(".day").removeClass("selected-day"); // Remove highlight from all days
                $(this).addClass("selected-day"); // Highlight clicked day
                // alert(`You clicked: ${year}-${month + 1}-${day}`); // Alert or any other action
            });
            $("#calendarDays").append(dayElement);
        }
    }

    function fillYearDropdown() {
        let year = currentDate.getFullYear();
        for (let i = year - 10; i <= year + 5; i++) {
            $('#yearSelect').append($('<option />').val(i).html(i));
        }
        $('#yearSelect').val(year);
    }

    $("#prev").click(function () {
        if (currentDate.getMonth() === 0) {
            currentDate = new Date(currentDate.getFullYear() - 1, 11);
        } else {
            currentDate = new Date(currentDate.getFullYear(), currentDate.getMonth() - 1);
        }
        fillCalendar(currentDate.getMonth(), currentDate.getFullYear());
    });

    $("#next").click(function () {
        if (currentDate.getMonth() === 11) {
            currentDate = new Date(currentDate.getFullYear() + 1, 0);
        } else {
            currentDate = new Date(currentDate.getFullYear(), currentDate.getMonth() + 1);
        }
        fillCalendar(currentDate.getMonth(), currentDate.getFullYear());
    });

    $("#yearSelect").change(function () {
        currentDate = new Date($(this).val(), currentDate.getMonth());
        fillCalendar(currentDate.getMonth(), currentDate.getFullYear());
    });

    fillYearDropdown();
    fillCalendar(currentDate.getMonth(), currentDate.getFullYear());
});
