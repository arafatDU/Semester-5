<?php
session_start();

if (isset($_SESSION['admin']) && $_SESSION['admin'] === true) {
    header("Location: contact-list.php");
    exit();
}
?>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Login</title>
    <link rel="stylesheet" href="1435.css">
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
                unset($_SESSION['message']);
            }
            ?>
        </div>
    </div>
</body>
</html>
