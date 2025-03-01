<?php
session_start();
include 'db_config.php';
?>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Login</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <?php include 'nav.php'; ?>
    <div class="mainContent container">

        <h1>Admin Login</h1>
        <form action="admin_login.php" method="POST">
            <label>Username:</label>
            <input type="text" name="username" placeholder="Enter username" required><br>
            <label>Password:</label>
            <input type="password" name="password" placeholder="Enter password" required><br>
            <button type="submit">Login</button>
        </form>
        <div class="message">
            <?php
            if (isset($_SESSION['message'])) {
                echo $_SESSION['message'];
                unset($_SESSION['message']); // Clear message after displaying
            }
            ?>
        </div>
    </div>
</body>
</html>
