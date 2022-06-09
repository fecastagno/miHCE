<?php
	include 'conexion.php';
	$idUsuario = $_POST["idUsuario"];
	
	$sentencia=$conexion->prepare("
			SELECT 	*
			FROM 	misdatos 
			WHERE 	id_usuario = ?");
	$sentencia->bind_param('s',$idUsuario);
	$sentencia->execute();

	$resultado = $sentencia->get_result();
	if ($fila = $resultado->fetch_assoc()) {
        echo json_encode($fila,JSON_UNESCAPED_UNICODE);     
	}

	$sentencia->close();
	$conexion->close();
?>