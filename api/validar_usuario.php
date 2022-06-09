<?php
	include 'conexion.php';
	$mail = $_POST["mail"];

	$sentencia=$conexion->prepare("SELECT password FROM usuario WHERE mail=?");
	$sentencia->bind_param('s',$mail);
	$sentencia->execute();

	$resultado = $sentencia->get_result();
	if ($fila = $resultado->fetch_assoc()) {
        echo json_encode($fila,JSON_UNESCAPED_UNICODE);     
	}
	
	$sentencia->close();
	$conexion->close();
?>