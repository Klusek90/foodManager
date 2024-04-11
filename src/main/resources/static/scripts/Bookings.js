let SelectedDate;

$(document).ready(function() {
    SelectedDate = new Date();
    let dd = String(SelectedDate.getDate()).padStart(2, '0');
    let mm = String(SelectedDate.getMonth() + 1).padStart(2, '0'); //January is 0!
    let yyyy = SelectedDate.getFullYear();

    SelectedDate = yyyy + '-' + mm + '-' + dd;
    updateBookings(SelectedDate);
});

$("#monthBtn").click(function() {
   updateBookings(SelectedDate)
});

function updateBookings(date) {

    $.ajax({
        url: '/bookings/'+ date,
        type: 'GET',
        dataType: 'json',
        success: function (response) {

            bookingListPopulator(response);

            // Handle successful response
            console.log('Data fetched successfully:', response);
            // Process the response data as needed
        },
        error: function (xhr, status, error) {
            // Handle error response
            console.error('Error fetching data:', error);
        }
    });
}

function bookingListPopulator(bookings){
    let bookingList = $('#bookingList');
    bookingList.empty(); // Clear existing content

    bookings.forEach(function(booking) {
        let bookingDiv = $('<div class="booking"></div>');
        let name = $('<p><span>Surname:</span> ' + booking.name + '<span>'+ +'</span></p>');
        let numberOfGuest = $('<p>Number of Guests: ' + booking.numberOfGuest + '</p>');

        bookingDiv.append(name, numberOfGuest);
        bookingList.append(bookingDiv);
    });
}