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

function bookingListPopulator(bookings) {
    let totalnumber = 0;
    let bookingList = $('#bookingList');
    bookingList.empty(); // Clear existing content

    // Create table element
    let table = $('<table class="bookingTable"></table>');

    bookings.forEach(function (booking) {
        // Create table row for each booking
        let row = $('<tr></tr>');

        // Populate table row with booking details
        row.append('<td>' + booking.name + '</td>');
        row.append('<td>' + booking.numberOfGuest + '</td>');

        // Append row to table
        table.append(row);

        // Increment total number of guests
        totalnumber += booking.numberOfGuest;
    });

    // Append table to bookingList div
    bookingList.append(table);

    // Update total number of guests
    $("#totalBookings").text(totalnumber);
}