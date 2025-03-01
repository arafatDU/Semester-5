<?php
session_start();
include 'db_config.php';

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $name = $_POST['name'];
    $email = $_POST['email'];
    $subject = $_POST['subject'];
    $messageContent = $_POST['message'];

    $sql = "INSERT INTO `1435` (name, email, subject, message) VALUES ('$name', '$email', '$subject', '$messageContent')";
    
    if ($conn->query($sql) === TRUE) {
        $_SESSION['message'] = "Message submitted successfully!";
    } else {
        $_SESSION['message'] = "Error: " . $sql . "<br>" . $conn->error;
    }

    $conn->close();

    // Redirect back to index.php after submission
    header("Location: contact.php");
    exit();
}
?>
