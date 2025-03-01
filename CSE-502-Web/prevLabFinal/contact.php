<?php
session_start();
include 'db_config.php';
?>
<!DOCTYPE html>
<html>
<head>
    <title>Contact Me</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <?php include 'nav.php'; ?>
    
    <div class="mainContent">
        <div class="container2">
            <h1>Contact Me</h1>
            <form action="submit_contact.php" method="POST">
                <label>Name:</label>
                <input type="text" name="name"placeholder="Enter name"required><br>
                <label>Email:</label>
                <input type="email" name="email" placeholder="Enter email" required><br>
                <label>Subject:</label>
                <input type="text" name="subject" placeholder="Enter subject" required><br>
                <label>Message:</label>
                <textarea name="message" placeholder="Enter message" required></textarea><br>
                <button type="submit">Submit</button>
            </form>
            <div class="message">
            <?php
            if (isset($_SESSION['message'])) {
                echo $_SESSION['message'];
                unset($_SESSION['message']); 
            }
            ?>
        </div>
        </div>
        
    </div>
</body>
</html>
