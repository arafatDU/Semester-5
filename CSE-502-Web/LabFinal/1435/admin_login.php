<?php
session_start();

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $username = $_POST['username'];
    $password = $_POST['password'];

    if ($username == "admin" && $password == "123456") {
        $_SESSION['admin'] = true;
        $_SESSION['message'] = "Login successful!"; 
        header("Location: contact-list.php");
        exit();
    } else {
        $_SESSION['message'] = "Invalid credentials!";
        header("Location: admin.php");
        exit();
    }
}
?>

