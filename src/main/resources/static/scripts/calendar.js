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
                $('#dayEventForm').removeClass('hidden');
                $(this).addClass("selected-day"); // Highlight clicked day
                $('#date').val(getSelectedDate());
                updateNotes();
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

    function getSelectedDate(){
       let day=  $('.selected-day').text();
       if(day.length<2){ day ="0"+day}
        let monthDigit;
        switch ($('#monthYear').text().toLowerCase()) {
            case 'january':
                monthDigit = '01';
                break;
            case 'february':
                monthDigit = '02';
                break;
            case 'march':
                monthDigit = '03';
                break;
            case 'april':
                monthDigit = '04';
                break;
            case 'may':
                monthDigit = '05';
                break;
            case 'june':
                monthDigit = '06';
                break;
            case 'july':
                monthDigit = '07';
                break;
            case 'august':
                monthDigit = '08';
                break;
            case 'september':
                monthDigit = '09';
                break;
            case 'october':
                monthDigit = '10';
                break;
            case 'november':
                monthDigit = '11';
                break;
            case 'december':
                monthDigit = '12';
                break;
            default:
                monthDigit = 'Invalid month';
        }
        let year= $('#yearSelect').val();

        return year+'-'+monthDigit.toString() +'-'+day;
    }


    function updateNotes() {
        let date = getSelectedDate(); // Example date, make sure it's a valid id format
        let dateId = date.replace(/-/g, ''); // Replace dashes to ensure the id is valid if needed
        let paragraphs = $('#' + dateId + ' p'); // Select paragraphs within the div
        let contents = [];

        paragraphs.each(function() {
            contents.push($(this).text()); // Collect each paragraph's content
        });

        console.log(contents);}


        $.ajax({
            url: "/greenboxbooking" // Adjust the URL to where your Spring Boot app is hosted
        }).then(function(data) {
            $('.booking-number').append(data.booking);
            $('.today-notes').append(data.notes);
        });
});
