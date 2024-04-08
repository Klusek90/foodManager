$('#prompt').on('show.bs.modal', function (e) {
    let products = []; // Tworzenie pustej tablicy na produkty

    // $('#productsList input:checked').each(function() { // Pobranie zaznaczonych produktów
    //     products.push($(this).val()); // Dodanie wartości do tablicy
    // });
    //
    // var additionalPrompt = $('#additionalPrompt').val(); // Pobranie wartości z dodatkowego promptu
    // var isVegan = $('#vegan').prop('checked'); // Pobranie wartości z checkboxa

    let product1 = 'ziemniak';
    let product2 = 'becon';
    let product3 = 'ser';

    // Dodanie produktów do tablicy
    products.push(product1);
    products.push(product2);
    products.push(product3);

    let additionalPrompt = "pieczone";
    let isVegan;

    // Wywołanie AJAX z danymi przekazywanymi do endpointu /request
    $.ajax({
        url: '/request',
        type: 'GET',
        // data: {
        //     products: products,
        //     additionalPrompt: additionalPrompt,
        //     vegan: isVegan
        // },
        success: function(response) {
            console.log(response); // Wyświetlenie odpowiedzi w konsoli
        },
        error: function(xhr, status, error) {
            console.error(xhr.responseText); // Wyświetlenie błędu w konsoli
        }
    });
});