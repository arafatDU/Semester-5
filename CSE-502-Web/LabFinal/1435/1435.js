document.addEventListener("DOMContentLoaded", function () {
  const form = document.getElementById("contactForm");

  form.addEventListener("submit", function (event) {
    if (!validateForm()) {
      event.preventDefault(); 
    }
  });

  function validateForm() {
    const name = form.name.value.trim();
    const email = form.email.value.trim();
    const subject = form.subject.value.trim();
    const message = form.message.value.trim();

    if (!name) {
      alert("Name is required.");
      return false;
    }

    if (!email) {
      alert("Email is required.");
      return false;
    }

    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailPattern.test(email)) {
      alert("Please enter a valid email address.");
      return false;
    }

    if (!subject) {
      alert("Subject is required.");
      return false;
    }

    if (!message) {
      alert("Message is required.");
      return false;
    }

    return true;
  }
});
