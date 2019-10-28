<?php
include "conexion.php";
$nombres = $_POST["nombres"];
$apellidos = $_POST["apellidos"];
$usuario = $_POST["usuarios"];
$clave = md5($_POST["claves"]);
$sql = "insert into usuario (idusuario,Nombres,Apellidos,Usuario,Clave,Estado) values(null,'$nombres','$apellidos', '$usuario', '$clave','1')";
print($sql);
$datos = array();
 if (mysqli_query($conn, $sql)) {
     print ('{"PHP": "Registrado"}');
 } else {
     print ('{"PHP": "Sql error"}');
 }
?>