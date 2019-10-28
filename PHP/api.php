<?php 
#header('Content-Type: application/json');
$method = $_SERVER['REQUEST_METHOD'];
switch ($method) {
 case 'POST': // create data
      $data = json_decode(file_get_contents('php://input'), true);  // true means you can convert data to array
    //  print_r($data);
      postOperation($data);
      break;
 	case 'GET': // read data
		  getOperation();
    	break;
  case 'PUT': // update data
      $data = json_decode(file_get_contents('php://input'), true);  // true means you can convert data to array
      putOperation($data);
      break;
  case 'DELETE': // delete data
      $data = json_decode(file_get_contents('php://input'), true);  // true means you can convert data to array
		  deleteOperation($data);
    	break;
  default:
    	print('{"PHP": "Requested http method not supported here."}');
}
// functions
  function putOperation($data){
      //Funciona
    include "conexion.php";
    $id = $data["idusuario"];
    $proveedor = $data["Nombres"];
    $ruc = $data["Apellidos"];
    $direccion = $data["Usuario"];
    $telefono = $data["Clave"];
    $sql = "UPDATE usuario SET Nombres = '$proveedor', Apellidos = '$ruc', Usuario = '$direccion', Clave = $telefono WHERE idusuario = '$id'";
    if (mysqli_query($conn, $sql) or die()) {
        echo '{"PHP": "Success"}';
    } else {
        echo '{"PHP": "Sql error"}';
    }
  }
  function getOperation(){
      //Funciona
    include "conexion.php";
    $sql = "SELECT * FROM usuario";
    $result = mysqli_query($conn, $sql);
    if (mysqli_num_rows($result) > 0) {
        // output data of each row
      $rows = array();
       while($r = mysqli_fetch_assoc($result)) {
          $rows[] = $r; // with result object
        //  $rows[] = $r; // only array
       }
      echo json_encode($rows);
    } else {
        print '{"PHP": "Data no encontrada"}';
    }
  }
  
  function postOperation($data){
   include "conexion.php";
   $nombres = $data["Nombres"];
   $apellidos = $data["Apellidos"];
   $usuario = $data["Usuarios"];
   $clave = md5($data["Claves"]);
   $sql = "insert into usuario (idusuario,Nombres,Apellidos,Usuario,Clave,Estado) values(null,'$nombres','$apellidos', '$usuario', '$clave','1')";
   print($sql);
   $datos = array();
    if (mysqli_query($conn, $sql)) {
        print ('{"PHP": "Registrado"}');
    } else {
        print ('{"PHP": "Sql error"}');
    }
  }
  function deleteOperation($data){
      //Funciona
    include "conexion.php";
    $id = $data["id"];
    $sql = "DELETE FROM proveedor WHERE idproveedor = $id";
    if (mysqli_query($conn, $sql)) {
        echo '{"PHP": "Realizado"}';
    } else {
        echo '{"PHP": "Sql error"}';
    }
  }
 ?>