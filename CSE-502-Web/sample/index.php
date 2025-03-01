<?php
include("header.php");
include("menu.php");
?>
<div id="main_content">
<br><br><br>
    <form action="actionpage.php" method="post">
        First Name: <input type="text" name="fname"/> <br>
        Last Name: <input type="text" name="lname"/><br>
        Email: <input type="email" name="email"/><br>
        <input type="submit" name="submit" value="Submit"/><br>
    </form>

</div>
<?php
include("footer.php");
?>