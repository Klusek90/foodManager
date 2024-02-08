
document.addEventListener("DOMContentLoaded", function() {
    var sidebarToggle = document.getElementById('sidebarToggle');
    sidebarToggle.addEventListener('click', function () {
        var navItems = document.querySelectorAll('.nav-item');
        navItems.forEach(function(item) {
            item.classList.toggle('show'); // Toggle visibility for all items, including the logout button
        });
    });
});