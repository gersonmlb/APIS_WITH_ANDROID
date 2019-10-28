<?php
include "conexion.php";
    $id = $_POST["idusuario"];
    $proveedor = $_POST["Nombres"];
    $ruc = $_POST["Apellidos"];
    $direccion = $_POST["Usuario"];
    $telefono = $_POST["Clave"];
    $sql = "UPDATE usuario SET Nombres = '$proveedor', Apellidos = '$ruc', Usuario = '$direccion', Clave = $telefono WHERE idusuario = '$id'";
    if (mysqli_query($conn, $sql) or die()) {
        echo '{"PHP": "Success"}';
    } else {
        echo '{"PHP": "Sql error"}';
    }

?>