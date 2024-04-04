$(document).ready(function() {
    $.ajax({
        url: "/greenboxbooking" // Adjust the URL to where your Spring Boot app is hosted
    }).then(function(data) {
        $('.booking-number').append(data.booking);
        $('.today-notes').append(data.notes);
    });
});