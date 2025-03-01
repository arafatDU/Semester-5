<!DOCTYPE html>
<html>
<head>
    <title>Contact Me</title>
    <link rel="stylesheet" href="1435.css">
</head>
<body>
    <?php include 'nav.php'; ?>

    <div class="mainContent">
        <div class="container2">
            <h1>Contact Me</h1>
            <form action="submit_contact.php" method="POST" id="contactForm">
                <label>Name:</label>
                <input type="text" name="name" placeholder="Enter name"><br>
                <label>Email:</label>
                <input type="text" name="email" placeholder="Enter email"><br>
                <label>Subject:</label>
                <input type="text" name="subject" placeholder="Enter subject"><br>
                <label>Message:</label>
                <textarea name="message" placeholder="Enter message"></textarea><br>
                <button type="submit">Submit</button>
            </form>

        </div>
    </div>

    <script src="1435.js"></script>
</body>
</html>
