<?php
session_start();
include 'db_config.php';

if (!isset($_SESSION['admin'])) {
    echo "Access Denied!";
    exit;
}

$result = $conn->query("SELECT * FROM `1435`");
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contact Messages</title>
    <link rel="stylesheet" href="1435.css">
</head>
<body>
    <?php include 'nav.php'; ?>

    <div class="mainContent">
        <div class="container2">
            <h1>Contact Messages</h1>
            <div class="table">

                <table>
                    <tr>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Subject</th>
                        <th>Message</th>
                    </tr>
                    <?php while ($row = $result->fetch_assoc()) : ?>
                        <tr>
                            <td><?php echo htmlspecialchars($row['name']); ?></td>
                            <td><?php echo htmlspecialchars($row['email']); ?></td>
                            <td><?php echo htmlspecialchars($row['subject']); ?></td>
                            <td><?php echo htmlspecialchars($row['message']); ?></td>
                        </tr>
                    <?php endwhile; ?>
                </table>
            </div>
            <a class="logout" href="logout.php">Logout</a>
        </div>
    </div>

</body>
</html>

<?php $conn->close(); ?>

