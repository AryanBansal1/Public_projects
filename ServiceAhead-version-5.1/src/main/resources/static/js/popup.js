// Get elements
const modal = document.getElementById("bookingModal");
const closeBtn = document.querySelector(".close-btn");
const bookNowButtons = document.querySelectorAll(".book-now-btn");
const confirmBtn = document.getElementById("confirmBtn");

// Open modal when any "Book Now" button is clicked
bookNowButtons.forEach(button => {
    button.addEventListener("click", function(event) {
        event.preventDefault(); // Prevent default link behavior
        modal.style.display = "block";
    });
});

// Close modal when the 'X' button is clicked
closeBtn.addEventListener("click", function() {
    modal.style.display = "none";
});

// Close modal when clicking outside the modal content
window.addEventListener("click", function(event) {
    if (event.target === modal) {
        modal.style.display = "none";
    }
});

// Confirm button event (you can add form validation here)
confirmBtn.addEventListener("click", function(event) {
    //event.preventDefault();
    alert("Booking confirmed!");
    //modal.style.display = "none";
    document.getElementById("bookingForm").submit();
});
