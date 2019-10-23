<?php
$dbhost = 'localhost:3306';
$dbuser = 'root';
$dbpass = '';
$bd = 'bd_almacen';
$conn = mysqli_connect($dbhost, $dbuser, $dbpass,$bd );
if( !$conn ){
   die('Could not connect: ' . mysqli_error());
}
?>